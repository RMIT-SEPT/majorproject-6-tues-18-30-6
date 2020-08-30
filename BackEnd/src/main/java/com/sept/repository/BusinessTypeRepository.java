package com.sept.repository;

import com.sept.models.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long> {

    Optional<BusinessType> findByName(String type);
}
