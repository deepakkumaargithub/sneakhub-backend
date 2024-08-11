package com.example.SpringBootRestApp.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootRestApp.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer>{

	void deleteByPid(int productId);
	 Cart findByUseridAndPid(BigInteger userID, int productId);
	 void deleteByUseridAndPid(BigInteger userID, int productId);
	Cart findByPid(int productId);
	 List<Cart> findByUserid(BigInteger userID);
	 void deleteByUserid(BigInteger userID);

}
