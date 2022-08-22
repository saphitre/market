package com.example.market;

import com.example.market.model.entity.Product;
import com.example.market.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MarketApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductRepository productRepository;

	Product product = new Product(1L,"Product",15);

	@Test
	void getProductById() throws Exception{
		Mockito.when(productRepository.getById(1L)).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/searchById/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void getAllProducts() throws Exception{
		List<Product> products=new ArrayList<>();
		products.add(product);

		Mockito.when(productRepository.findAll()).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/products")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}


}
