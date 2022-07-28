package com.iihtproject.bidservice.dto.response;

import lombok.Data;

@Data
public class BidResponseDto {
    private String buyerFirstName;
    private String buyerLastName;
    private String buyerAddress;
    private String buyerCity;
    private String buyerState;
    private String buyerPinCode;
    private String buyerPhone;
    private String buyerEmail;
    private String productId;
    private Double bidAmount;
}
