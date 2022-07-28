package com.iihtproject.productservice.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.iihtproject.productservice.dto.request.CreateProductRequestDto;

public class CreateProductTest {
	
	public CreateProductRequestDto should_create_new_product() throws Exception {
		CreateProductRequestDto product = new CreateProductRequestDto();
		product.setBidEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-22"));
		product.setCategory("Painting");
		product.setDetailedDescription("Painting by a person");
		product.setProductName("A Painting");
		product.setSellerAddress("AAAAAAAAAA");
		product.setSellerCity("AAAAAAAAAA");
		product.setSellerEmail("AAAAAAAAAA@gmail");
		product.setStartingPrice(1000);
		product.setSellerFirstName("AAAAAAAAAA");
		product.setSellerLastName("AAAAAAAAAAA");
		product.setSellerPhone("AAAAAAAAAA");
		product.setSellerPinCode("AAAAAAAAA");
		product.setSellerState("AAA");
		product.setShortDescription("AAA");
		product.setSellerState("AAA");
		return product;
	}

}
