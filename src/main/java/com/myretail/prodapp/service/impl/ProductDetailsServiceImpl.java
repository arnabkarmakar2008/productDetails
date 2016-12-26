package com.myretail.prodapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myretail.prodapp.dao.ProductDetailsDAO;
import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;
import com.myretail.prodapp.jsonmapper.Product;
import com.myretail.prodapp.jsonmapper.ProductWrapper;
import com.myretail.prodapp.service.ProductDetailsService;
import com.myretail.prodapp.util.ProductDetailsUtil;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

	private static Logger logger = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);
	
	@Autowired(required = true)
	private ProductDetailsDAO productDetailsDAO;
	
	@Value("${product.details.url}")
	private String productDetailsURL;
	
	@Override
	public ProductDetails processProductPriceDetails (long productId) {
		logger.info("Entered ProductDetailsServiceImpl.processProductPriceDetails() with ProductId :: " + productId);
		
		//Call DAO to get Product Price Details
		ProductPrice productPrice = productDetailsDAO.getProductPrice(productId);		
		
		//Fetch ProductDetails from External Service
		Product product = fetchProductDetails(productId);
		
		ProductDetails productDetails = ProductDetailsUtil.mergeProductDetails(productPrice, product);	
		
		logger.info("Exited ProductDetailsServiceImpl.processProductPriceDetails()");
		
		return productDetails;
	}
	
	@Override
	public ProductDetails updateProductPriceDetails(ProductDetails productDetails) {
		logger.info("Entered ProductDetailsServiceImpl.updateProductPriceDetails() with ProductId :: " + productDetails.getId());
		
		productDetails = productDetailsDAO.updateProductPrice(productDetails);
		
		logger.info("Exited ProductDetailsServiceImpl.updateProductPriceDetails()  productDetails :: " + productDetails);
		
		return productDetails;
	}
	
	/**
	 * Call External Service to get product details
	 * @param productId
	 * @return Product
	 */
	private Product fetchProductDetails (long productId) {
		logger.info("Entered ProductDetailsServiceImpl.fetchProductDetails() with ProductId :: " + productId);
		ProductWrapper wrapper = null;
		Product productDetails = null;
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ProductWrapper> response = restTemplate.getForEntity(getProductDetailsURL(), ProductWrapper.class, productId);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				wrapper = response.getBody();
				productDetails = wrapper.getProduct();
				logger.info("Retrieved Product Details for ID :: "+ productId + " Details :: " +productDetails);
			}
		}
		catch(Exception exp) {
			logger.error("Exception in External Service Call", exp);
		}

		logger.info("Exited ProductDetailsServiceImpl.fetchProductDetails()");
		return productDetails;
	}

	public String getProductDetailsURL() {
		return productDetailsURL;
	}

	public void setProductDetailsURL(String productDetailsURL) {
		this.productDetailsURL = productDetailsURL;
	}	
	
}
