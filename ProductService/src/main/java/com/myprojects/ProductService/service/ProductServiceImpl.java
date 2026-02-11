package com.myprojects.ProductService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.ProductService.Exception.ProductServiceCustomException;
import com.myprojects.ProductService.entity.Product;
import com.myprojects.ProductService.model.ProductRequest;
import com.myprojects.ProductService.model.ProductResponse;
import com.myprojects.ProductService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
	
	
//	We need object of repo here as we are implementing 
//	addproduct in db here
	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding Product..");
		Product product=
				Product.builder()
				.productName(productRequest.getName())
				.quantity(productRequest.getQuantity())
				.price(productRequest.getPrice())
				.build();
		productRepository.save(product);
		log.info("Product Created");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		//list of type entity
		log.info("Get product for :" + productId);
		Product product=
				productRepository.findById(productId)
				.orElseThrow(()-> new ProductServiceCustomException("Product not found","NOT FOUND"));
		
		ProductResponse productResponse= new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);

		return productResponse;
		
		
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		//list of type entity
		List<Product> productList= productRepository.findAll();
		List<ProductResponse> products = 
				productList
				.stream()
				.map(product ->{
					ProductResponse productResponse = new ProductResponse();
					BeanUtils.copyProperties(product, productResponse);
					return productResponse;
				}).collect(Collectors.toList());
		return products;
		
		

	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("Reduce Quantity {} id :{}",quantity,productId);
		Product product= 
				productRepository.findById(productId)
				.orElseThrow(()-> new ProductServiceCustomException(
						"Product with given Id not found", "PRODUCT_NOT_FOUND"));
		if(product.getQuantity()<quantity) {
			throw new ProductServiceCustomException(
					"Product does not have sufficient Quantity",
					"INSUFFICIENT_QUANTITY");
		
		}
		product.setQuantity(product.getQuantity()-quantity);
		productRepository.save(product);
		log.info("Quantity updated");
	}

}
