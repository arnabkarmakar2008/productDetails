package com.myretail.prodapp.jsonmapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	
	private String tcin;
	private String upc;
	private ProductDescription product_description;
	
	public String getTcin() {
		return tcin;
	}
	
	public void setTcin(String tcin) {
		this.tcin = tcin;
	}
	
	public String getUpc() {
		return upc;
	}
	
	public void setUpc(String upc) {
		this.upc = upc;
	}

	public ProductDescription getProduct_description() {
		return product_description;
	}

	public void setProduct_description(ProductDescription product_description) {
		this.product_description = product_description;
	}	
	
	
}
