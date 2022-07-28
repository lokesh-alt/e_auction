package com.iihtproject.productservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iihtproject.productservice.dto.request.CreateProductRequestDto;
import com.iihtproject.productservice.dto.response.ProductResponse;
import com.iihtproject.bidservice.service.BidService;
import com.iihtproject.exception.customException.CustomException;
import com.iihtproject.productservice.model.ProductEntity;
import com.iihtproject.productservice.repository.ProductRepository;
import com.iihtproject.productservice.service.ProductService;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private BidService bidService;

    public ProductResponse addProduct(CreateProductRequestDto createProductRequestDto){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(createProductRequestDto,productEntity);
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(productRepository.save(productEntity), productResponse);
        return productResponse;
    }

    @Override
    public boolean deleteProduct(String productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isEmpty()) {
            throw new CustomException("Given product id is unavailable");
        }

        ProductEntity product = productEntity.get();

        Date currentDate = new Date();
        if (currentDate.compareTo(product.getBidEndDate()) > 0) {
            throw new CustomException("Product cannot be deleted after bid end date");
        }

        int numOfBids = bidService.getBids(productId).size();
        if (numOfBids > 0) {
            throw new CustomException("At least one Bid has been placed on the product");
        }
        productRepository.deleteById(productId);
        
        return true;
    }

	@Override
	public ProductResponse showBids(@Valid String id) {
        Optional<ProductEntity> productEntity = productRepository.findByIdAndBidEndDateGreaterThan(id, new Date());
        if (productEntity.isEmpty()) {
            throw new CustomException("Given product id is unavailable or attempting to delete after bid end date over");
        }
        
        ProductResponse response = new ProductResponse();
        ProductEntity entity = productEntity.get();
        response.setBidEndDate(entity.getBidEndDate());
        response.setCategory(entity.getCategory());
        response.setDetailedDescription(entity.getDetailedDescription());
        response.setId(entity.getId());
        response.setProductName(entity.getProductName());
        response.setSellerAddress(entity.getSellerAddress());
        response.setSellerCity(entity.getSellerCity());
        response.setSellerEmail(entity.getSellerEmail());
        response.setSellerFirstName(entity.getSellerFirstName());
        response.setSellerLastName(entity.getSellerLastName());
        response.setSellerPhone(entity.getSellerPhone());
        response.setSellerPinCode(entity.getSellerPinCode());
        response.setSellerState(entity.getSellerState());
        response.setShortDescription(entity.getShortDescription());
        response.setStartingPrice(entity.getStartingPrice());
        response.setBidList(bidService.getBids(id));
        
        return  response;
	}

	@Override
	public boolean findProductByIdAndBidEndDate(@Valid String id, Date date) {
		Optional<ProductEntity> productEntity = productRepository.findByIdAndBidEndDateLessThan(id,date);
		
		 if (productEntity.isEmpty()) {
	            throw new CustomException("Given product id is unavailable");
	        }
	        
		return true;
	}
}
