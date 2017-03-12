package com.myretail.prodapp.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.myretail.prodapp.dao.document.ProductPriceDocument;
import com.myretail.prodapp.dao.impl.ProductDetailsDAOImpl;
import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;


public class ProductDetailsDAOImplTest {

	@InjectMocks
	ProductDetailsDAOImpl productDetailsDAOImpl;
	
	@Mock
	MongoOperations mongo;
	
	@Mock 
	WriteResult writeResultMock;

	//Comment
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetProductPrice() {
		
		long productId = 13860428;
		ProductPriceDocument priceDocument = new ProductPriceDocument();
		priceDocument.setCode("USD");
		priceDocument.setId(new Long(13860428));
		priceDocument.setPrice(100.12);
		
		ProductPrice productPrice = new ProductPrice(100.12, "USD");
		
		when(mongo.findById(productId, ProductPriceDocument.class)).thenReturn(priceDocument);
		
		ProductPrice productPriceDAOReturn = productDetailsDAOImpl.getProductPrice(productId);
		
		assertEquals(productPriceDAOReturn.getCurrencyCode(), productPrice.getCurrencyCode());
		assertEquals(productPriceDAOReturn.getValue(), productPrice.getValue(),0.001);				
	}
	
	@Test
	public void testUpdateProductPrice() {
		
		ProductPrice productPrice = new ProductPrice(100.12, "USD");
		ProductDetails productDetails = new ProductDetails(13860428, "The Big Lebowski (Blu-ray)", productPrice);
		
		long productId = 13860428;
		ProductPriceDocument priceDocument = new ProductPriceDocument();
		priceDocument.setCode("USD");
		priceDocument.setId(new Long(13860428));
		priceDocument.setPrice(100.12);
		
		when(mongo.findById(productId, ProductPriceDocument.class)).thenReturn(priceDocument);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(productDetails.getId()));
		query.fields().include("id");
		
		Update update = new Update();
		update.set("price", productDetails.getProductPrice().getValue());
		update.set("code", productDetails.getProductPrice().getCurrencyCode());
		
		when(mongo.updateFirst(query, update, ProductPriceDocument.class)).thenReturn(writeResultMock);
		
		ProductDetails productDetailsReturn = productDetailsDAOImpl.updateProductPrice(productDetails);
		
		assertEquals(productDetailsReturn.getId(), productDetails.getId());
		assertEquals(productDetailsReturn.getName(), productDetails.getName());
		assertEquals(productDetailsReturn.getProductPrice().getValue(), productDetails.getProductPrice().getValue(), 0.001);
	}
}
