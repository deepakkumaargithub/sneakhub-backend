package com.example.SpringBootRestApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootRestApp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{

	 List<Product> findByNameContaining(String name);
}
