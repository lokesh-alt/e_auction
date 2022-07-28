package com.iihtproject.bidservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.iihtproject.bidservice.dto.request.CreateBidRequestDto;
import com.iihtproject.bidservice.dto.response.BidResponseDto;
import com.iihtproject.exception.customException.CustomException;
import com.iihtproject.productservice.repository.ProductRepository;
import com.iihtproject.bidservice.model.BidEntity;
import com.iihtproject.bidservice.repository.BidRepository;
import com.iihtproject.bidservice.service.BidService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    BidRepository bidRepository;
    
    @Autowired
    ProductRepository productRepository;
    
    @Override
    public BidResponseDto placeBid(CreateBidRequestDto createBidRequestDto) {
        Optional<BidEntity> optionalBidEntity= bidRepository.findByBuyerEmail(createBidRequestDto.getBuyerEmail());
        if(optionalBidEntity.isPresent()){
            throw new CustomException("A Buyer has already place the Bid with Email-ID: "+createBidRequestDto.getBuyerEmail());
        }
        if(productRepository.findById(createBidRequestDto.getProductId()).isEmpty()) {
        	throw new CustomException("Product with given ID does not exist");
        	
        }
        BidEntity bidEntity = new BidEntity();
        BeanUtils.copyProperties(createBidRequestDto,bidEntity);
        BidResponseDto bidResponseDto = new BidResponseDto();
        BeanUtils.copyProperties(bidRepository.save(bidEntity),bidResponseDto);
        return bidResponseDto;
    }

    @Override
    public BidResponseDto updateBid(String productId, String buyerEmail, Double newBidAmount) {
//        Criteria criteria = new Criteria();
//        criteria.andOperator(Criteria.where("productId").is(bidDto.getProductId()),
//                Criteria.where("buyerEmail").is(bidDto.getBuyerEmail()));
//        Query query = new Query(criteria);
        Optional<BidEntity> bid = bidRepository.findByProductIdAndBuyerEmail(productId, buyerEmail);
        if(bid.isEmpty()) {
        	throw new CustomException("Unable to find bid given ID, E-Mail ID and Bid Date");
        }
        
        BidEntity bidEntity = bid.get();
        bidEntity.setBidAmount(newBidAmount);
        BidResponseDto bidResponseDto = new BidResponseDto();
        BeanUtils.copyProperties(bidRepository.save(bidEntity),bidResponseDto);
        return bidResponseDto;
    }

    @Override
    public List<BidResponseDto> getBids(String productId) {
        List<BidEntity> bidEntities = bidRepository.findAllByProductId(productId);
        List<BidResponseDto> bidResponseDtos = new ArrayList<>();
        bidEntities.forEach(bidEntity -> {
            BidResponseDto biResponse = new BidResponseDto();
            BeanUtils.copyProperties(bidEntity,biResponse);
            bidResponseDtos.add(biResponse);
        });
        return bidResponseDtos;
    }
}
