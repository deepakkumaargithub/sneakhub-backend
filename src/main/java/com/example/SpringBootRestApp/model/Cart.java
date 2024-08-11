package com.example.SpringBootRestApp.model;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Cart_id;
	private BigInteger userid;
	private int pid;
	private int quantity;
	private float price;
	private String imageURL;
	private String name;
	private String brand;
	 
	public Cart(){}

	public int getCart_id() {
		return Cart_id;
	}

	public void setCart_id(int cart_id) {
		Cart_id = cart_id;
	}

	public BigInteger getUserid() {
		return userid;
	}

	public void setUserid(BigInteger userid) {
		this.userid = userid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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


	
}
