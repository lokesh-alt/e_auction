package com.iihtproject.bidservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iihtproject.bidservice.model.BidEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BidRepository extends MongoRepository<BidEntity,String> {
    Optional<BidEntity> findByProductIdAndBuyerEmail(String productId,String buyerEmail);

    List<BidEntity> findAllByProductId(String productId);

    Optional<BidEntity> findByBuyerEmail(String buyerEmail);

	Optional<BidEntity> findByProductIdAndBuyerEmailAndBidEndDateGreaterThan(String productId, String buyerEmail, Date date);
}
