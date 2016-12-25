package com.myretail.prodapp.dao.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productprice")
public class ProductPriceDocument {

	@Id
	private Long id;
	
	private Double price;
	
	private String code;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "ProductPriceDocument [id=" + id + ", price=" + price + ", code=" + code + "]";
	}	
	
}
