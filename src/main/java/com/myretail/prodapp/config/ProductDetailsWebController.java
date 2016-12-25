package com.myretail.prodapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SuppressWarnings("deprecation")
@SpringBootApplication
@ComponentScan("com.myretail.prodapp")
public class ProductDetailsWebController extends SpringBootServletInitializer {
	
	public static void main(String args[]) {
		SpringApplication.run(ProductDetailsWebController.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProductDetailsWebController.class);
    }
}
