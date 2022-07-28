package com.iihtproject.bidservice.dto;

import com.iihtproject.bidservice.dto.request.CreateBidRequestDto;

public class CreateBidTest {

	public CreateBidRequestDto shouldCreateBidRequestDto() {
		CreateBidRequestDto bid = new CreateBidRequestDto();
		bid.setBidAmount(10000.0);
		bid.setBuyerAddress("AAAAA");
		bid.setBuyerCity("AAAA");
		bid.setBuyerEmail("anc@ghkkk");
		bid.setBuyerFirstName("aaaaa");
		bid.setBuyerLastName("aaaaa");
		bid.setBuyerPhone("1234567890");
		bid.setBuyerPinCode("123456");
		bid.setBuyerState("AAAAAA");
		
		return bid;
	}
	
}
