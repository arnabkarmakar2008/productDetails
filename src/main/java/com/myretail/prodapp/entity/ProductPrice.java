package com.myretail.prodapp.entity;

public class ProductPrice {

	private double value;
	private String currencyCode;
	
	public ProductPrice() {
		
	}
	
	public ProductPrice(double value, String currencyCode) {
		this.value = value;
		this.currencyCode = currencyCode;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "ProductPrice [value=" + value + ", currencyCode=" + currencyCode + "]";
	}	
	
}
