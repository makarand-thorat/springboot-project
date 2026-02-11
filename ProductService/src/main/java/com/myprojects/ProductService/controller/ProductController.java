package com.myprojects.ProductService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.ProductService.model.ProductRequest;
import com.myprojects.ProductService.model.ProductResponse;
import com.myprojects.ProductService.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	//all implementation of product service used here so we inject its 
	//object here
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAnyRole('Admin', 'Customer')")
	@PostMapping
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
		long productId = productService.addProduct(productRequest);
		return new ResponseEntity<>(productId,HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyRole('Admin', 'Customer')")
	@GetMapping
	public List<ProductResponse> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PreAuthorize("hasAnyRole('Admin', 'Customer')")
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id")long productId){
		ProductResponse productResponse
		= productService.getProductById(productId);
		
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('Admin', 'Customer')")
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(
			@PathVariable("id") long productId,
			@RequestParam long quantity
			){
	productService.reduceQuantity(productId,quantity);
	return new ResponseEntity<>(HttpStatus.OK);
	}
}
