package com.example.demo.services;

import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.TransactionDetails;
import org.springframework.http.ResponseEntity;

public interface PaymentFacade {

    ResponseEntity<ResponseModel> makePurchase(TransactionDetails transactionDetails);
}