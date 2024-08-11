package com.example.SpringBootRestApp.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootRestApp.model.Cart;
import com.example.SpringBootRestApp.service.CartService;

@RestController
@CrossOrigin
public class CartController {

	 @Autowired
	    private CartService cartService;
	 
        //API for adding any product to cart
	    @PostMapping("/cart/add/{userID}/{pid}/{quantity}")
	    public ResponseEntity<String> addToCart(@PathVariable BigInteger userID,@PathVariable int pid, @PathVariable int quantity) {
	        cartService.addToCart(userID,pid, quantity);
	        return ResponseEntity.ok("Product added to cart successfully");
	    }

	    //API for deleting items from cart
	    @DeleteMapping("/cart/remove/{userID}/{productId}")
	    public ResponseEntity<String> removeFromCart(@PathVariable BigInteger userID,@PathVariable int productId) {
	        cartService.removeFromCart(userID,productId);
	        return ResponseEntity.ok("Product removed from cart successfully");
	    }
	    
	    //API to display all the items in the cart
	    @GetMapping("/cart")
	    public List<Cart> getCartProducts(){
	    	return cartService.getCartProducts();
	    }
	    
	    //API to fetch the items in the cart of a particular user
	    @GetMapping("/cart/{userID}")
	    public List<Cart>  getCartProductsByUserId(@PathVariable BigInteger userID){
	    	return cartService.getCartProductsByUserId(userID);
	    }
	    
	   
	    
	    //API to update quantity of a product in the cart
	    @PutMapping("/cart/update/{userID}/{productId}/{quantity}")
	    public ResponseEntity<String> updateQuantity(@PathVariable BigInteger userID,@PathVariable int productId, @PathVariable int quantity) {
	        cartService.updateQuantity(userID,productId, quantity);
	        return ResponseEntity.ok("Quantity updated successfully");
	    }

	    @DeleteMapping("/cart/clear/{userID}")
	    public ResponseEntity<String> clearCart(@PathVariable BigInteger userID) {
	        cartService.clearCart(userID);
	        return ResponseEntity.ok("Cart cleared successfully for userID: " + userID);
	    }
	    
	    
}
