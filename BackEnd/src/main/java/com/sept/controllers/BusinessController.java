package com.sept.controllers;

import com.sept.models.Business;
import com.sept.models.BusinessType;
import com.sept.payload.request.BusinessRegisterRequest;
import com.sept.payload.response.MessageResponse;
import com.sept.repository.BusinessRepository;
import com.sept.repository.BusinessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class BusinessController {

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    BusinessTypeRepository businessTypeRepository;


    @PostMapping("/registerBusiness")
    public ResponseEntity<?> registerBusiness(@Valid @RequestBody BusinessRegisterRequest registerRequest) {
        if (businessRepository.existsByabn(registerRequest.getAbn())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: A business has already registered under that ABN"));
        }

        BusinessType type = businessTypeRepository.findByName(registerRequest.getType()).orElseThrow(() -> new RuntimeException("Error: Invalid business type"));

        Business business = new Business(registerRequest.getName(), registerRequest.getAbn(), type);
        businessRepository.save(business);

        return ResponseEntity.ok(new MessageResponse("Business has been registered successfully!"));
    }
}
