package com.myretail.prodapp.entity;

public class ProductDetails {

	private long id;
	private String name;
	private ProductPrice productPrice;
	
	public ProductDetails() {
		
	}
	
	public ProductDetails(long id, String name, ProductPrice productPrice) {
		this.id = id;
		this.name = name;
		this.productPrice = productPrice;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ProductPrice getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productPrice=" + productPrice + "]";
	}

	
}
