package com.adidas.shopshoes.repository;

import com.adidas.shopshoes.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType, String> {
}
