package com.example.SpringBootRestApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootRestApp.model.Product;
import com.example.SpringBootRestApp.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService service;

	// API to fetch any single product
	@GetMapping("/products/{pid}")
	public Optional<Product> getProduct(@PathVariable String pid) {
		return service.getProduct(Integer.parseInt(pid));
	}

	// API to fetch all the products
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}

// to fetch products for pagination
	@GetMapping("/products/from/{start}/to/{end}")
	public List<Product> getProductsInRange(@PathVariable("start") int start, @PathVariable("end") int end) {
		List<Product> allProducts = service.getAllProducts();

		if (start < 1 || start > allProducts.size() || end < start || end > allProducts.size()) {
			throw new IllegalArgumentException("Invalid range parameters");
		}
		return allProducts.subList(start - 1, end);
	}

	// API to search any product with its name
	@GetMapping("/products/search/{name}")
	public ResponseEntity<List<Product>> searchProductByName(@PathVariable String name) {
		List<Product> products = service.searchProductByName(name);
		return ResponseEntity.ok(products);
	}

}
