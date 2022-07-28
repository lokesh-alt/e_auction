package com.iihtproject.productservice.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.iihtproject.bidservice.dto.response.BidResponseDto;

@Data
public class ProductResponse {
    private String id;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private String category;
    private Double startingPrice;
    private Date bidEndDate;
    private String sellerFirstName;
    private String sellerLastName;
    private String sellerAddress;
    private String sellerCity;
    private String sellerState;
    private String sellerPinCode;
    private String sellerPhone;
    private String sellerEmail;
    
    private List<BidResponseDto> bidList;
}
