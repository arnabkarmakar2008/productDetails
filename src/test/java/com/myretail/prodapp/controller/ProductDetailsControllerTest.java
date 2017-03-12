package com.myretail.prodapp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.myretail.prodapp.config.ProductDetailsWebController;
import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;
import com.myretail.prodapp.service.ProductDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProductDetailsWebController.class})
@WebAppConfiguration
public class ProductDetailsControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	ProductDetailsService productDetailsService;
	
	ProductDetailsController instance;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		instance = new ProductDetailsController();
		ReflectionTestUtils.setField(instance, "productDetailsService", productDetailsService);
		mockMvc = MockMvcBuilders.standaloneSetup(instance).build();

	}
	
	@Test
	public void testProductPriceById() throws Exception {
		
		//Comment
		ProductPrice productPrice = new ProductPrice(100.12, "USD");
		ProductDetails productDetails = new ProductDetails(13860428, "The Big Lebowski (Blu-ray)", productPrice);
		long productId = 13860428;
		
		when(productDetailsService.processProductPriceDetails(productId)).thenReturn(productDetails);
		
		mockMvc.perform(get("/products/13860428"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is(13860428)))
		.andExpect(jsonPath("name", is("The Big Lebowski (Blu-ray)")))
		.andExpect(jsonPath("productPrice.value", is(100.12)))
		.andExpect(jsonPath("productPrice.currencyCode", is("USD")));
				
		verify(productDetailsService, times(1)).processProductPriceDetails(productId);				
	}
		
	@Test
	public void testProductPriceById_ProductNotFound() throws Exception {		
		long productId = 1386042800;		
		when(productDetailsService.processProductPriceDetails(productId)).thenReturn(null);		
		mockMvc.perform(get("/products/1386042800"))
		.andExpect(status().isNotFound());
		
		verify(productDetailsService, times(1)).processProductPriceDetails(productId);				
	}	
	
	@Test
	public void testUpdateProductPrice() throws Exception {
		ProductPrice productPrice = new ProductPrice(103.12, "USD");
		ProductDetails productDetails = new ProductDetails(13860428, "The Big Lebowski (Blu-ray)", productPrice);
		
		when(productDetailsService.updateProductPriceDetails(productDetails)).thenReturn(productDetails);
		
		ResponseEntity<ProductDetails> productDetailsReturnEntity = instance.updateProductPrice(13860428, productDetails);
		
		ProductDetails productDetailsReturn = (ProductDetails)productDetailsReturnEntity.getBody();
		
		assertEquals(productDetailsReturn.getId(), productDetails.getId());
		assertEquals(productDetailsReturn.getName(), productDetails.getName());
		assertEquals(productDetailsReturn.getProductPrice().getCurrencyCode(), productDetails.getProductPrice().getCurrencyCode());
		assertEquals(productDetailsReturn.getProductPrice().getValue(), productDetails.getProductPrice().getValue(),0.001);		
		verify(productDetailsService, times(1)).updateProductPriceDetails(productDetails);
	}

}
