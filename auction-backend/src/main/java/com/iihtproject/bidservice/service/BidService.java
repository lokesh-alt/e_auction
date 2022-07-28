package com.iihtproject.bidservice.service;

import java.util.List;

import com.iihtproject.bidservice.dto.request.CreateBidRequestDto;
import com.iihtproject.bidservice.dto.response.BidResponseDto;

public interface BidService {
    BidResponseDto placeBid(CreateBidRequestDto createBidRequestDto);
    BidResponseDto updateBid(String productId, String buyerEmail, Double newBidAmount);
    List<BidResponseDto> getBids(String productId);
}
