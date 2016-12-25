package com.myretail.prodapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;
import com.myretail.prodapp.jsonmapper.Product;

public class ProductDetailsUtil {

	private static Logger logger = LoggerFactory.getLogger(ProductDetailsUtil.class);
	
	public static ProductDetails mergeProductDetails(ProductPrice productPrice, Product product ) {
		
		ProductDetails productDetails = null;
		
		if(productPrice == null || product == null) {
			return null;
		}
		else {
			try {
				String productName = product.getItem().getProduct_description().getTitle();
				long id = Long.parseLong(product.getItem().getTcin());
				
				productDetails = new ProductDetails(id, productName, productPrice);
			}
			catch(Exception exp) {
				logger.error("Error Fetching Product Details while Merging", exp);
			}
		}
		
		return productDetails;
	}
}
