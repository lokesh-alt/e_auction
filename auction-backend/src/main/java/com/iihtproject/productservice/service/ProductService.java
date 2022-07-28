package com.iihtproject.productservice.service;

import java.util.Date;

import javax.validation.Valid;

import com.iihtproject.productservice.dto.request.CreateProductRequestDto;
import com.iihtproject.productservice.dto.response.ProductResponse;

public interface ProductService {
    
	ProductResponse addProduct(CreateProductRequestDto createProductRequestDto);
    
    boolean deleteProduct(String productId);

	ProductResponse showBids(@Valid String id);

	boolean findProductByIdAndBidEndDate(@Valid String id, Date date);
}
