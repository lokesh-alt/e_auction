package com.iihtproject.bidservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.iihtproject.bidservice.dto.request.CreateBidRequestDto;
import com.iihtproject.bidservice.dto.response.BidResponseDto;
import com.iihtproject.bidservice.service.BidService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/e-auction/api/v1/buyer")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/place-bid")
    public  ResponseEntity<BidResponseDto> placeBid(@Valid @RequestBody CreateBidRequestDto createBidRequestDto){
        return new ResponseEntity<>(bidService.placeBid(createBidRequestDto),HttpStatus.OK);
    }

    @PutMapping("/update-bid/{productId}/{buyerEmailId}/{newBidAmount}")
    public ResponseEntity<BidResponseDto> updateBid(@PathVariable("productId") String productId,
                                            @PathVariable("buyerEmailId") String buyerEmail,
                                            @PathVariable("newBidAmount") Double newBidAmount){
        return new ResponseEntity<>(bidService.updateBid(productId,buyerEmail,newBidAmount),HttpStatus.OK);
    }

    @GetMapping("/getBids/{productId}")
    public ResponseEntity<List<BidResponseDto>> getAllBids(@PathVariable("productId") String productId){
        return new ResponseEntity<>(bidService.getBids(productId),HttpStatus.OK);
    }
}
