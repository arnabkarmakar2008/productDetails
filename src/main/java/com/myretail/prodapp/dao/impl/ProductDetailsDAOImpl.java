package com.myretail.prodapp.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.myretail.prodapp.dao.ProductDetailsDAO;
import com.myretail.prodapp.dao.document.ProductPriceDocument;
import com.myretail.prodapp.entity.ProductDetails;
import com.myretail.prodapp.entity.ProductPrice;
import com.myretail.prodapp.service.impl.ProductDetailsServiceImpl;

@Repository
public class ProductDetailsDAOImpl implements ProductDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);
	
	@Autowired
	MongoOperations mongo;
	
	@Override
	public ProductPrice getProductPrice(long productId) {
		logger.debug("Entered ProductDetailsDAOImpl.getProductPrice() with Product ID :: "+ productId);
		
		ProductPrice productPrice = null;
		
		try {
			ProductPriceDocument productPriceDocument = mongo.findById(productId, ProductPriceDocument.class);			
			
			if(productPriceDocument != null) {
				productPrice = new ProductPrice(productPriceDocument.getPrice(),productPriceDocument.getCode());
			}
			
			logger.info("ProductDetailsDAOImpl.getProductPrice() DB ProductPrice :: "+ productPrice);		
		} 
		catch(Exception exp) {
			logger.error("ProductDetailsDAOImpl.getProductPrice() :: Error fetching data from DB",exp);
		}
		logger.debug("Exited ProductDetailsDAOImpl.getProductPrice() with Product ID :: "+ productId);
		return productPrice;
	}
	
	@Override
	public ProductDetails updateProductPrice(ProductDetails productDetails) {
		logger.debug("Entered ProductDetailsDAOImpl.updateProductPrice() with Product ID :: "+ productDetails.getId());
		
		try {
			ProductPriceDocument productPriceDocument = mongo.findById(productDetails.getId(), ProductPriceDocument.class);
			
			if(productPriceDocument != null) {
				
				Query query = new Query();
				query.addCriteria(Criteria.where("id").is(productDetails.getId()));
				query.fields().include("id");
				
				Update update = new Update();
				update.set("price", productDetails.getProductPrice().getValue());
				update.set("code", productDetails.getProductPrice().getCurrencyCode());
				
				mongo.updateFirst(query,update,ProductPriceDocument.class);				

			}
			else {
				logger.error("ProductDetailsDAOImpl.updateProductPrice(). Product Not Found to Update with ID :: "+productDetails.getId());
				productDetails = null;
			}
		}
		catch(Exception exp) {
			logger.error("Error in ProductDetailsDAOImpl.updateProductPrice()", exp);
			productDetails = null;
		}	
		
		logger.debug("Exited ProductDetailsDAOImpl.updateProductPrice() productDetails:: "+ productDetails);
		
		return productDetails;
	}

}
