package com.myapp.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.myapp.spring.model.Product;
import com.myapp.spring.repository.ProductRepository;

//@SpringBootTest
@WebMvcTest
class SpringDevopsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductRepository productRepository;

	@Test
	public void testGetProducts() throws Exception {
		List<Product> products = new ArrayList<>();

		Product product1 = new Product();
		product1.setProductName("Iphone12");

		product1.setPrice(95000.0);
		product1.setDescription("Iphone12mini");

		products.add(product1);

		Product product2 = new Product();
		product2.setProductName("Ipad");

		product2.setPrice(45000.0);
		product2.setDescription("Ipadpro");
		products.add(product2);

		Mockito.when(productRepository.findAll()).thenReturn(products);
		mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].productName", Matchers.equalTo("Iphone12")));
	}
}