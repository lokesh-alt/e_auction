package com.iihtproject.bidservice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iihtproject.AuctionServiceApplication;
import com.iihtproject.bidservice.dto.CreateBidTest;
import com.iihtproject.bidservice.dto.request.CreateBidRequestDto;
import com.iihtproject.productservice.dto.CreateProductTest;
import com.iihtproject.productservice.dto.request.CreateProductRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuctionServiceApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ControllerE2ETests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private final CreateProductTest createProductTest = new CreateProductTest();
	private final CreateBidTest createBidTest = new CreateBidTest();
	
	
	@Test
	@DisplayName("BID CREATION - SUCCESS")
	@WithMockUser(roles = {"ADMIN"})
	public void should_create_bid_test() throws Exception {
		CreateProductRequestDto createProductRequest = createProductTest.should_create_new_product();
		JSONObject createdProduct = createProduct(createProductRequest);
		CreateBidRequestDto createBid = createBidTest.shouldCreateBidRequestDto();
		createBid.setProductId(createdProduct.getString("id"));
		String body = objectMapper.writeValueAsString(createBid);
		mockMvc
		.perform(MockMvcRequestBuilders.post("/e-auction/api/v1/buyer/place-bid").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(body))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	private JSONObject createProduct(CreateProductRequestDto product) throws Exception {
		String body = objectMapper.writeValueAsString(product);
		String response = mockMvc
		.perform(MockMvcRequestBuilders.post("/e-auction/api/v1/seller/add-product").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(body))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated()).andReturn().getResponse()
		.getContentAsString();
		
		return new JSONObject(response);
	}

}
