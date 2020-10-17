package com.sept.backend.services;

import com.sept.backend.model.Booking;
import com.sept.backend.model.Business;
import com.sept.backend.model.User;
import com.sept.backend.repositories.BusinessRepository;
import com.sept.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    public Business saveOrUpdateBusiness(Business business){



        return businessRepository.save(business);
    }

    public List<Business> getAll(){
        List<Business> businesses = new ArrayList<Business>();
        businessRepository.findAll().forEach(b -> businesses.add(b));
        return businesses;
    }

    public Optional<Business> getBusinessById(Long id){
        return businessRepository.findById(id);
    }

}
