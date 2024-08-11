package com.example.SpringBootRestApp.service;

import java.util.List;
import java.util.Optional;

import com.example.SpringBootRestApp.model.Product;

public interface ProductService{

//	public List<Product> getAllProducts();
	public Optional<Product> getProduct(int pid);
	public List<Product> getAllProducts();
	 public List<Product> searchProductByName(String name);
	
}
