package com.myretail.prodapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.exception.ProductNotFoundException;
import com.myretail.prodapp.service.ProductDetailsService;
import com.myretail.prodapp.service.impl.ProductDetailsServiceImpl;

@RestController
@PropertySource("classpath:productDetails.properties")
public class ProductDetailsController {

	private static Logger logger = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);
	
	@Autowired
	ProductDetailsService productDetailsService;
	
	@RequestMapping(value = "/products/{id}" , method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<ProductDetails> productPriceById(@PathVariable long id) {
		logger.info("Entered ProductDetailsController.productPriceById()");
		ProductDetails productDetails = productDetailsService.processProductPriceDetails(id);
		
		if(productDetails == null) {
			return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
		}
		logger.info("Exited ProductDetailsController.productPriceById()");
		return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}" , method = RequestMethod.PUT, produces="application/json", consumes="application/json")
	public ResponseEntity<ProductDetails> updateProductPrice(@PathVariable long id, @RequestBody ProductDetails productDetails) {
		logger.info("Entered ProductDetailsController.updateProductPrice()");
		
		productDetails = productDetailsService.updateProductPriceDetails(productDetails);
		
		if(productDetails == null) {
			return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exited ProductDetailsController.updateProductPrice()");
		
		return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error productNotFound(ProductNotFoundException exp) {
		long productId = exp.getProductId();
		return new Error("Product [" + productId + "] not found.");
	}
	
	
}
