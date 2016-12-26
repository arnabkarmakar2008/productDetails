package com.myretail.prodapp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.myretail.prodapp.dao.ProductDetailsDAO;
import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;
import com.myretail.prodapp.jsonmapper.Item;
import com.myretail.prodapp.jsonmapper.Product;
import com.myretail.prodapp.jsonmapper.ProductDescription;
import com.myretail.prodapp.service.impl.ProductDetailsServiceImpl;


public class ProductDetailsServiceImplTest {

	@InjectMocks
	ProductDetailsServiceImpl productDetailsServiceImpl;
	
	@Mock
	private ProductDetailsDAO productDetailsDAO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testProcessProductPriceDetails() {
		
		ProductPrice productPrice = new ProductPrice(100.12, "USD");
		
		ProductDescription prodDesc = new ProductDescription();
		prodDesc.setTitle("The Big Lebowski (Blu-ray)");
		prodDesc.setLong_description("The plot of this Raymond Chandler-esque comedy crime "
				+ "caper from the Coen Brothers (Joel Coen and Ethan Coen) pivots around a case of "
				+ "mistaken identity complicated by extortion, double-crosses, deception, embezzlement, sex, pot, "
				+ "and gallons of White Russians (made with fresh cream, please). In 1991, unemployed '60s "
				+ "refugee Jeff \"The Dude\" Lebowski (Jeff Bridges) grooves into his laid-back Los Angeles lifestyle. "
				+ "One of the laziest men in LA,");
		
		Item item = new Item();
		item.setUpc("025192110306");
		item.setTcin("13860428");
		item.setProduct_description(prodDesc);
		
		Product prod = new Product();
		prod.setItem(item);
		
		long productId = 13860428;
		
		productDetailsServiceImpl.setProductDetailsURL("http://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
		
		when(productDetailsDAO.getProductPrice(productId)).thenReturn(productPrice);
		
		ProductDetails productDetails = new ProductDetails(13860428, "The Big Lebowski (Blu-ray)", productPrice);
		
		ProductDetails productDetailsServiceReturn = productDetailsServiceImpl.processProductPriceDetails(13860428);
		
		assertEquals(productDetailsServiceReturn.getId(), productDetails.getId());
		assertEquals(productDetailsServiceReturn.getName(), productDetails.getName());
		assertEquals(productDetailsServiceReturn.getProductPrice().getCurrencyCode(), productDetails.getProductPrice().getCurrencyCode());
		assertEquals(productDetailsServiceReturn.getProductPrice().getValue(), productDetails.getProductPrice().getValue(),0.001);		
		
	}
	
	@Test
	public void testUpdateProductPriceDetails() {
		ProductPrice productPrice = new ProductPrice(100.12, "USD");
		ProductDetails productDetails = new ProductDetails(13860428, "The Big Lebowski (Blu-ray)", productPrice);
		
		when(productDetailsDAO.updateProductPrice(productDetails)).thenReturn(productDetails);
		
		ProductDetails productDetailsServiceReturn = productDetailsServiceImpl.updateProductPriceDetails(productDetails);
		
		assertEquals(productDetailsServiceReturn.getId(), productDetails.getId());
		assertEquals(productDetailsServiceReturn.getName(), productDetails.getName());
		assertEquals(productDetailsServiceReturn.getProductPrice().getCurrencyCode(), productDetails.getProductPrice().getCurrencyCode());
		assertEquals(productDetailsServiceReturn.getProductPrice().getValue(), productDetails.getProductPrice().getValue(),0.001);	
	}
	
}
