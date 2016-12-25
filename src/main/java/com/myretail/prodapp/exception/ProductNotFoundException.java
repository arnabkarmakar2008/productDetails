package com.myretail.prodapp.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 17019635830305822L;
	
	private long productId;

	public ProductNotFoundException(long productId) {
		this.productId = productId;
	}

	public long getProductId() {
		return productId;
	}			
	
}
