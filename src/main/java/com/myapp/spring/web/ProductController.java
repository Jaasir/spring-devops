package com.myapp.spring.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.Product;
import com.myapp.spring.repository.ProductRepository;

@RestController
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository repository;

	@RequestMapping(method = RequestMethod.GET, value = "products")
	public @ResponseBody ResponseEntity<List<Product>> getProducts() {
		logger.info("Processing products request");

		List<Product> products = repository.findAll();

		return ResponseEntity.ok(products);
	}

}