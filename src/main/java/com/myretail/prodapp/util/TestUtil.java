package com.myretail.prodapp.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	public static String convertObjectToJsonString(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, true);
        return mapper.writeValueAsString(object);
    }
	
//	public static void main(String args[]) throws IOException {
//		ProductPrice productPrice = new ProductPrice(103.12, "USD");
//		ProductDetails productDetails = new ProductDetails(13860428, "The Big Lebowski (Blu-ray)", productPrice);
//		
//		byte[] arr = convertObjectToJsonBytes(productDetails);
//		
//		System.out.println(new String(arr));
//	}
}
