package com.productsearch;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProductSearch {

	// Directory where CSV files are located
	private static final String CSV_DIRECTORY = "src/main/resources/csv";

	// Thread poll new csv at this interval

	private static final long CSV_POLL_INTERVAL_MS = 10_000; // 10 seconds

	// Flag indicating when to stop thread
	private volatile boolean stopRequested;

	// Thread to poll new csv Files
	private Thread fileLoaderThread;

	// Set containing the names of all CSV files that have been read
	private HashSet<String> csvFileRead;

	public ProductSearch() {
		this.csvFileRead = new HashSet<>();
		// this.products = new ArrayList<>();
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
				String fileName = getFileName(csvFile.toString());

				if (csvFileRead.contains(fileName)) {
					continue;
				} else {
					csvFileRead.add(fileName);
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
							this.saveTShirt(tShirt);

						}
					} catch (NumberFormatException e) {
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
			System.err.println(
					"Error: The CSV directory path is invalid or You do not have the necessary permissions to access the CSV directory.\n csv file folder be present in src/main/resources folder"
							+ e.getMessage());
			this.stop();
		}

	}

	// Extracts the name of a file from its full path
	private String getFileName(String path) {
		String[] parts = path.split("\\\\");
		String fileName = parts[parts.length - 1];
		return fileName;
	}

	// Function to save Tshirt in DB
	public void saveTShirt(TShirt tshirt) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// Check if the record with the same primary key already exists
			TShirt existingTShirt = (TShirt) session.get(TShirt.class, tshirt.getId());
			if (existingTShirt == null) {
				// If the record does not exist, insert the new record
				session.save(tshirt);
				tx.commit();
			} else {
				// If the record already exists, do nothing
				System.err.println("TShirt with id " + tshirt.getId() + " already exists.");
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			if(e instanceof JDBCConnectionException){
				this.stop();
			}
			e.printStackTrace();
			
		} finally {
			session.close();
		}
	}

	// Function to Search TShirt in DB
	private List<TShirt> searchTShirts(String color, String size, String gender, String outputPreference) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<TShirt> tshirts = null;
		try {
			tx = session.beginTransaction();
			String hql = getQuery(outputPreference);
			Query query = session.createQuery(hql);
			query.setParameter("color", color);
			query.setParameter("size", size);
			query.setParameter("gender", gender);
			tshirts = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			
			if(e instanceof JDBCConnectionException){
				this.stop();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tshirts;
	}

	// Starts a new thread that polls the CSV directory for changes
	private void startCsvPollingThread() {
		fileLoaderThread = new Thread(() -> {
			while (!stopRequested) {
				try {
					Thread.sleep(CSV_POLL_INTERVAL_MS);
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
		if (color == null || color.isEmpty() || size == null || size.isEmpty() || gender == null || gender.isEmpty()
				|| outputPreference == null || outputPreference.isEmpty()) {
			throw new IllegalArgumentException("color size gender outputPreference parameters cannot be null or empty");
		}

		List<TShirt> matchingItem = searchTShirts(color, size, gender, outputPreference);
		return matchingItem;
	}

	// Get Query String
	private String getQuery(String outputPreference) {
		String q = "FROM TShirt WHERE colour = :color AND size = :size AND genderRecommendation = :gender ";
		if (outputPreference.equalsIgnoreCase("Price")) {
			q += "order by price";
		} else if (outputPreference.equalsIgnoreCase("Rating")) {
			q += "order by rating";
		} else if (outputPreference.equalsIgnoreCase("Both")) {
			q += "order by price,rating";
		} else {
			System.err.println("Invalid output preference: " + outputPreference);
		}
		return q;
	}

}
