package com.example.SpringBootRestApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {


    @Id
    private int id;
  
    private String name;
    private String brand;
    private String gender;
    private String category;
    private float price;
    private boolean is_in_inventory;
    private int items_left;
    private String imageURL;
    private String slug;
    
    public Product(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isIs_in_inventory() {
		return is_in_inventory;
	}

	public void setIs_in_inventory(boolean is_in_inventory) {
		this.is_in_inventory = is_in_inventory;
	}

	public int getItems_left() {
		return items_left;
	}

	public void setItems_left(int items_left) {
		this.items_left = items_left;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
    
    
    
}
