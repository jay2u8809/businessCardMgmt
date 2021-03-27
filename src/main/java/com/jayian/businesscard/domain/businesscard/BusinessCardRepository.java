package com.jayian.businesscard.domain.businesscard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCardRepository extends JpaRepository<BusinessCard, Long> {
}
