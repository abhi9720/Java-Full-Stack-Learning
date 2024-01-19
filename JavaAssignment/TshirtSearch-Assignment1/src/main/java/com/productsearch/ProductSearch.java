package com.productsearch;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;


public class ProductSearch {

	// Directory where CSV files are located
	private static final String CSV_DIRECTORY = "src/main/resources/csv";

	// Thread poll new csv at this interval
	private static final long CHECK_INTERVAL_MS = 10000; // 10 seconds

	// List of all products loaded from the CSV files
	private List<TShirt> products;

	// Flag indicating when to stop thread
	private boolean stopRequested;

	// Thread to poll new csv Files
	private Thread fileLoaderThread;

	//Set containing the names of all CSV files that have been read
	private HashSet<String> csvFileReaded ;

	public ProductSearch() {
		this.csvFileReaded =  new HashSet<>();
		this.products = new ArrayList<>();
		this.loadCsvFiles();
		this.startCsvPollingThread();
		this.stopRequested = false;	
	}

	// Loads all CSV files from the CSV_DIRECTORY
	private void loadCsvFiles() {
		try {
		    File csvDir = new File(CSV_DIRECTORY);
		   
		    File[] csvFiles = csvDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
			for (File csvFile : csvFiles) {
				String fileName =  getFileName(csvFile.toString());
				
				if(csvFileReaded.contains(fileName)) {
					continue;
				}
				else {
					csvFileReaded.add(fileName);
				}
				
				try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

					try (@SuppressWarnings("deprecation")
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
						for (CSVRecord record : csvParser) {
							String id = record.get("ID");
							String name = record.get("NAME");
							String colour = record.get("COLOUR");
							String gender = record.get("GENDER_RECOMMENDATION");
							String size = record.get("SIZE");
							double price = Double.parseDouble(record.get("PRICE"));
							double rating = Double.parseDouble(record.get("RATING"));
							boolean availability = record.get("AVAILABILITY").equalsIgnoreCase("Y");
							TShirt tShirt = new TShirt(id, name, colour, gender, size, price, rating, availability);
							this.products.add(tShirt);
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				 // Stop loading files when user input is detected
	            if (stopRequested) {
	                break;
	            }
			}
		} catch (NullPointerException | SecurityException | IllegalArgumentException e) {
			System.err.println("Error: The CSV directory path is invalid or You do not have the necessary permissions to access the CSV directory.\n csv file folder be present in src/main/resources folder" + e.getMessage());
			this.stop();
		}

		
	}

	// Extracts the name of a file from its full path
	private String getFileName(String path) {
		String[] parts = path.split("\\\\");
		String fileName = parts[parts.length-1];
		return fileName;
	}
	
	// Starts a new thread that polls the CSV directory for changes
	private void startCsvPollingThread() {
		fileLoaderThread = new Thread(() -> {
			while (!stopRequested) {
				try {
					Thread.sleep(CHECK_INTERVAL_MS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.loadCsvFiles();
			}
		});
		fileLoaderThread.start();
	}

	// function to STOP fileLoaderThread 
	public void stop() {
		stopRequested = true;
		try {
			fileLoaderThread.join();
		} catch (InterruptedException e) {
			System.err.println("Error stopping file loader thread: " + e.getMessage());
		}
	}

	// Return All Matching Item as per user Query
	public List<TShirt> MatchingItem(String color, String size, String gender, String outputPreference) {
		List<TShirt> matchingItem = new ArrayList<TShirt>();

		for (TShirt product : products) {
			if (product.getColour().equalsIgnoreCase(color) && product.getSize().equalsIgnoreCase(size)
					&& (product.getGenderRecommendation().equalsIgnoreCase(gender)
							|| product.getGenderRecommendation().equalsIgnoreCase("U"))) {
				matchingItem.add(product);
			}
		}
		sortTShirts(matchingItem, outputPreference);
		return matchingItem;
	}
	
	// Sort all the MatchingItem result as per input outputPreference
	private void sortTShirts(List<TShirt> tshirts, String outputPreference) {
		if (outputPreference.equalsIgnoreCase("Price")) {
			Collections.sort(tshirts, Comparator.comparingDouble(TShirt::getPrice));
		} else if (outputPreference.equalsIgnoreCase("Rating")) {
			Collections.sort(tshirts, Comparator.comparingDouble(TShirt::getRating));
		} else if (outputPreference.equalsIgnoreCase("Both")) {
			Collections.sort(tshirts,
					Comparator.comparingDouble(TShirt::getPrice).thenComparingDouble(TShirt::getRating));
		} else {
			System.err.println("Invalid output preference: " + outputPreference);
		}
	}

}
