package com.example.SpringBootRestApp.service;

import java.math.BigInteger;
import java.util.List;

import com.example.SpringBootRestApp.model.Cart;

public interface CartService {

	
	 public void addToCart(BigInteger userID, int productId, int quantity);
	 public void removeFromCart(BigInteger userID,int productId);
	 public List<Cart> getCartProducts();
	 public void updateQuantity(BigInteger userID,int productId, int quantity) ;
	 List<Cart> getCartProductsByUserId(BigInteger userId);
	 public void clearCart(BigInteger userID);

}
