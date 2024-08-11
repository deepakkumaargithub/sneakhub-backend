package com.example.SpringBootRestApp.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootRestApp.model.Cart;
import com.example.SpringBootRestApp.model.Product;
import com.example.SpringBootRestApp.repository.CartRepo;
import com.example.SpringBootRestApp.repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {
	
	  @Autowired
	    private CartRepo cartRepository;

	    @Autowired
	    private ProductRepo productRepository;

	    @Transactional
	    public void addToCart(BigInteger userID, int productId, int quantity) {
	        Cart existingCartItem = cartRepository.findByUseridAndPid( userID,productId);
	        if (existingCartItem != null) {
	            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
	            cartRepository.save(existingCartItem);
	        } else {
	            Product product = productRepository.findById(productId).orElse(null);
	            if (product != null) {
	                Cart newCartItem = new Cart();
	                newCartItem.setUserid(userID);
	                newCartItem.setPid(productId);
	                newCartItem.setQuantity(quantity);
	                newCartItem.setPrice(product.getPrice() * quantity);
	                newCartItem.setImageURL(product.getImageURL());
	                newCartItem.setName(product.getName());
	                newCartItem.setBrand(product.getBrand());
	                cartRepository.save(newCartItem);
	            }
	        }
	    }

	    @Transactional
	    public void removeFromCart(BigInteger userID,int productId) {
	    	cartRepository.deleteByUseridAndPid(userID, productId);
	    }
	    
	    public List<Cart> getCartProducts(){
	    	return cartRepository.findAll();
	    }
	    
	    @Transactional
	    public void updateQuantity(BigInteger userID, int productId, int quantity) {
	        Cart cartItem = cartRepository.findByUseridAndPid(userID, productId);
	        if (cartItem != null) {
	            cartItem.setQuantity(quantity);
	            cartRepository.save(cartItem);
	        }
	    }

	    
	    public List<Cart> getCartProductsByUserId(BigInteger userId) {
	        return cartRepository.findByUserid(userId);
	    }
	    
	    @Transactional
	    public void clearCart(BigInteger userID) {
	        cartRepository.deleteByUserid(userID);
	    }

}
