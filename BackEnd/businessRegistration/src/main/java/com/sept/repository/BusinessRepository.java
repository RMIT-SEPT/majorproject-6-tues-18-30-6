package com.sept.repository;

import com.sept.models.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sept.models.Business;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findByabn(int abn);

    Boolean existsByabn(int abn);


}
