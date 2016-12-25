package com.myretail.prodapp.service;

import com.myretail.prodapp.entity.ProductDetails;

public interface ProductDetailsService {

	public ProductDetails processProductPriceDetails(long productId);
	
	public ProductDetails updateProductPriceDetails(ProductDetails productDetails);
	
}
