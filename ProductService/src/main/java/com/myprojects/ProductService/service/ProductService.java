package com.myprojects.ProductService.service;

import java.util.List;

import com.myprojects.ProductService.model.ProductRequest;
import com.myprojects.ProductService.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	List<ProductResponse> getAllProducts();

	void reduceQuantity(long productId, long quantity);

}
