package com.iihtproject.productservice.repository;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iihtproject.productservice.model.ProductEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

	Optional<ProductEntity> findByIdAndBidEndDateLessThan(@Valid String id, Date date);

	Optional<ProductEntity> findByIdAndBidEndDateGreaterThan(@Valid String id, Date date);
}
