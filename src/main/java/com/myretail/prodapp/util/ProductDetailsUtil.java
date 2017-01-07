package com.myretail.prodapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;
import com.myretail.prodapp.jsonmapper.Product;

public class ProductDetailsUtil {

	private static Logger logger = LoggerFactory.getLogger(ProductDetailsUtil.class);
	
	/**
	 * Utility method to merge product details fetched from DB and External Service
	 * @param productPrice - Product Price fetched from DB
	 * @param product - Product Details fetched from external service
	 * @return Merged ProductDetails. Null if either productPrice or product is null.
	 */
	public static ProductDetails mergeProductDetails(ProductPrice productPrice, Product product ) {
		
		ProductDetails productDetails = null;
		
		if(productPrice == null || product == null) {
			return null;
		}
		else {
			try {
				String productName = product.getItem().getProduct_description().getTitle();
				long id = product.getAvailable_to_promise_network().getProduct_id();

				productDetails = new ProductDetails(id, productName, productPrice);
			}
			catch(Exception exp) {
				logger.error("Error Fetching Product Details while Merging", exp);
			}
		}
		
		return productDetails;
	}
}
