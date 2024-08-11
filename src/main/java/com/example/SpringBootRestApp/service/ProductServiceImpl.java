package com.example.SpringBootRestApp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootRestApp.model.Product;
import com.example.SpringBootRestApp.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo repo;
	
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	
	public Optional <Product> getProduct(int pid) {
		return repo.findById(pid);
	}
	
	 public List<Product> searchProductByName(String name) {
	        return repo.findByNameContaining(name);
	    }
	
	

}
