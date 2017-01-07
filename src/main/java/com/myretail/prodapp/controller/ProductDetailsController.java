package com.myretail.prodapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.prodapp.entity.ProductDetails;
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
		
		logger.debug("Entered ProductDetailsController.productPriceById()");
		
		//Call Service Method to get Product Details 
		ProductDetails productDetails = productDetailsService.processProductPriceDetails(id);
		
		/*
		 * If productDetails == null, then Product is not found in DB due to data not present
		 * or due to some exception in the Service or DAO layer
		 */
		if(productDetails == null) {
			logger.info("Product Price Not Found for Product ID :: " + id);
			return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
		}
		
		logger.info("Product Price Found :: " + productDetails);
		
		logger.debug("Exited ProductDetailsController.productPriceById()");
		
		return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<ProductDetails> updateProductPrice(@PathVariable long id, @RequestBody ProductDetails productDetails) {
		
		logger.debug("Entered ProductDetailsController.updateProductPrice()");
		
		//Call Service Method to update Product
		productDetails = productDetailsService.updateProductPriceDetails(productDetails);
		
		/*
		 * If productDetails == null, then Product is not found in DB due to data not present
		 * or due to some exception in the Service or DAO layer
		 */
		if(productDetails == null) {
			logger.info("Product Price Not Found for Product ID :: " + id);
			return new ResponseEntity<ProductDetails>(HttpStatus.NOT_FOUND);
		}
		
		logger.info("Product Price Updated :: " + productDetails);
		
		logger.debug("Exited ProductDetailsController.updateProductPrice()");
		
		return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
	}		
	
}
