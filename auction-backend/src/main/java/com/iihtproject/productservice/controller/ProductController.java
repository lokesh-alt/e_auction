package com.iihtproject.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.iihtproject.productservice.dto.request.CreateProductRequestDto;
import com.iihtproject.productservice.dto.response.ProductResponse;
import com.iihtproject.productservice.service.ProductService;

import java.util.Date;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/e-auction/api/v1/seller")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World",HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse>addProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDto){
        return new ResponseEntity<>(productService.addProduct(createProductRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("show-bids/{productId}")
    public ResponseEntity<ProductResponse> showBidsForProduct(@Valid @PathVariable("productId") String id){
    	
    	ProductResponse response = productService.showBids(id);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    	
    }
    
    @GetMapping("find-product/{productId}/{bidEndDate}")
    public ResponseEntity<Boolean> findProductByIdAndBidEndDate(@Valid @PathVariable("productId")String id, @PathVariable("bidEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
    	boolean response = productService.findProductByIdAndBidEndDate(id,date);
    	return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") String productId) {
        boolean deleted = productService.deleteProduct(productId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
