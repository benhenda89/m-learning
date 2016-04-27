package com.atec.learning.classification.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.service.CatalogService;

public class RunnableExample {
	@Resource(name ="blCatalogService")
	protected static  CatalogService catalogservice;

    public static void main(String[] args) {

       
    	final RdrClassificationUtils<Long, Long> bayes =
                new BayesClassifier<Long, Long>();
    
       List<Product> products=catalogservice.findAllProducts();
    	Map<Long, List<Long>> categoryProduts = new HashMap<Long, List<Long>>();
		
		for (Product productId:products)
		{
			
			Product product = catalogservice.findProductById(productId.getId());
			if(Objects.nonNull(product)){
				Category cat = product.getCategory();
				 bayes.learn(cat.getId(),products);
				if(categoryProduts.get(cat.getId()) != null){
					List<Long> prods = categoryProduts.get(cat.getId());
						prods.add(productId.getId());
						categoryProduts.put(cat.getId(), prods);
						 System.out.println( // will output "positive"
					                bayes.classify(prods));
						 ((BayesClassifier<Long, Long>) bayes).classifyDetailed(
					                prods);
				} else {
					List<Long> prods = new ArrayList<Long>();
					prods.add(productId.getId());
					categoryProduts.put(cat.getId(), prods);
					 System.out.println( // will output "positive"
				                bayes.classify(prods));
				}
				 bayes.setMemoryCapacity(500); // remember the last 500 learned classifications
}
		}
    }

	}
