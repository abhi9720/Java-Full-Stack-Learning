package com.webapp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.csv.*;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.model.Tshirt;

@Service
@Transactional
public class CsvServiceImpl implements CsvService {

	@Autowired
	TshirtService tshirtservice;

	// Directory where CSV files are located
	private static final String CSV_DIRECTORY = "C:\\Users\\abhishektiwari02\\eclipse-workspace2\\Assignment4\\src\\main\\resources\\csv";

	// Thread poll new csv at this interval

	private static final long CSV_POLL_INTERVAL_MS = 10000; // 10 seconds

	// Flag indicating when to stop thread
	private volatile boolean stopRequested;

	// Thread to poll new csv Files
	private Thread fileLoaderThread;

	// Map containing the names of all CSV files that have been read
	private ConcurrentHashMap<String, Boolean> csvFileRead = new ConcurrentHashMap<>();

	private void loadCsvFiles() {

		try {

			System.out.println("----------Thread Running ------------");

			File csvDir = new File(CSV_DIRECTORY);

			File[] csvFiles = csvDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

			for (File csvFile : csvFiles) {
				String fileName = getFileName(csvFile.toString());

				// Skip If file already read
				if (csvFileRead.putIfAbsent(fileName, true) != null) {
					continue;
				}

				try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

					try (@SuppressWarnings("deprecation")
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
						for (CSVRecord record : csvParser) {
							String productId = record.get("ID");
							String name = record.get("NAME");
							String colour = record.get("COLOUR");
							String gender = record.get("GENDER_RECOMMENDATION");
							String size = record.get("SIZE");
							double price = Double.parseDouble(record.get("PRICE"));
							double rating = Double.parseDouble(record.get("RATING"));
							boolean availability = record.get("AVAILABILITY").equalsIgnoreCase("Y");

							Tshirt tShirt = new Tshirt(productId,name, colour, gender, size, price, rating, availability);
							tshirtservice.saveOrUpate(tShirt);

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

		} catch (NullPointerException e) {
			System.err.println(
					"Error: The CSV directory path is invalid or You do not have the necessary permissions to access the CSV directory.\n csv file folder be present in src/main/resources folder"
							+ e.getMessage());
			stopCsvPollingThread();
		}
	}

	public void startCsvPollingThread() {
		Runnable rn = new Runnable() {

			public void run() {
				while (!stopRequested) {
					try {
						Thread.sleep(CSV_POLL_INTERVAL_MS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					loadCsvFiles();
				}
			}
		};
		fileLoaderThread = new Thread(rn);
		fileLoaderThread.start();

	}

	public void stopCsvPollingThread() {
		stopRequested = true;
		try {
			fileLoaderThread.join();
		} catch (InterruptedException e) {
			// handle the exception as needed
		}
	}

	// Extracts the name of a file from its full path
	private String getFileName(String path) {
		String[] parts = path.split("\\\\");
		String fileName = parts[parts.length - 1];
		return fileName;
	}

	@PreDestroy
	public void cleanup() {
		stopCsvPollingThread();
	}

}
