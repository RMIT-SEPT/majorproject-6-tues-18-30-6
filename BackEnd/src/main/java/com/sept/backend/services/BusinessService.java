package com.sept.backend.services;

import com.sept.backend.model.Business;
import com.sept.backend.model.User;
import com.sept.backend.repositories.BusinessRepository;
import com.sept.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    public Business saveOrUpdateBusiness(Business business){



        return businessRepository.save(business);
    }

}
