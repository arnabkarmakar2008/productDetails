package com.myretail.prodapp.jsonmapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	
	private Item item;
	
	private AvailablePromiseNetwork available_to_promise_network;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public AvailablePromiseNetwork getAvailable_to_promise_network() {
		return available_to_promise_network;
	}

	public void setAvailable_to_promise_network(AvailablePromiseNetwork available_to_promise_network) {
		this.available_to_promise_network = available_to_promise_network;
	}			
	
}
