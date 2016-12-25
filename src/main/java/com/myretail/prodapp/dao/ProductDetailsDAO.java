package com.myretail.prodapp.dao;

import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;

public interface ProductDetailsDAO {

	public ProductPrice getProductPrice(long productId);
	
	public ProductDetails updateProductPrice(ProductDetails productDetails);
}
