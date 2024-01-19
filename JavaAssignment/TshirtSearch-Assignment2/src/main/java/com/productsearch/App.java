package com.productsearch;

import java.util.List;
import java.util.Scanner;

public class App {

	private static Scanner scn;

	public static void main(String[] args) {
		
		// Wait for some time to allow the file loader thread to load the initial CSV files
	    try {
			System.out.println("Reading CSV FILES ... ");
	        Thread.sleep(5000);
	        System.out.println("Done !!!");
	    } catch (InterruptedException e) {
	        System.err.println("Error sleeping main thread: " + e.getMessage());
	    }


		scn = new Scanner(System.in);
		ProductSearch pdr =  new ProductSearch();


		do {
			System.out.println("Search Produt: (Format: color size gender outputPreference) ");
			String color = scn.next();
			String size = scn.next();
			String gender = scn.next();
			String outputPreference = scn.next();

			// Display the matching products
			List<TShirt> matchingProducts =  pdr.MatchingItem(color,size,gender, outputPreference);

			if (matchingProducts.isEmpty()) {
				System.out.println("No matching products found.");
			} else {
				System.out.println("Matching products:");
				for (TShirt product : matchingProducts) {
					System.out.println(product);
				}
			}
			System.out.println("Do you want to search for more products? (y/n)");
		}
		while(scn.next().equalsIgnoreCase("y"));
		pdr.stop();
				
	}
}
