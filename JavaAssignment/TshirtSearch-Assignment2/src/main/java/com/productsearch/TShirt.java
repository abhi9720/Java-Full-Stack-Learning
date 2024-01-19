package com.productsearch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tshirts")
public class TShirt {
	@Id
	private String id;
	private String name;
	private String colour;
	
	@Column(name="gender")
	private String genderRecommendation;
	private String size;
	private double price;
	private double rating;
	private boolean availability;

	public TShirt() {
		
	}

	public TShirt(String id, String name, String colour, String genderRecommendation, String size, double price,
			double rating, boolean availability) {
		super();
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.genderRecommendation = genderRecommendation;
		this.size = size;
		this.price = price;
		this.rating = rating;
		this.availability = availability;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getGenderRecommendation() {
		return genderRecommendation;
	}

	public void setGenderRecommendation(String genderRecommendation) {
		this.genderRecommendation = genderRecommendation;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "TShirt [id=" + id + ", name=" + name + ", colour=" + colour + ", genderRecommendation="
				+ genderRecommendation + ", size=" + size + ", price=" + price + ", rating=" + rating
				+ ", availability=" + availability + "]";
	}
	

}