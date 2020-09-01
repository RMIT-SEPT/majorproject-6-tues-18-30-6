package com.sept.repository;

import com.sept.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findByabn(int abn);

    Boolean existsByabn(int abn);


}
