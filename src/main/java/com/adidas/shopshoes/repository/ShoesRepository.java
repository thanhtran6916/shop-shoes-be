package com.adidas.shopshoes.repository;

import com.adidas.shopshoes.model.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, String> {
}
