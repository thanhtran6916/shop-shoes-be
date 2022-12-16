package com.adidas.shopshoes.repository;

import com.adidas.shopshoes.model.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, String> {
}
