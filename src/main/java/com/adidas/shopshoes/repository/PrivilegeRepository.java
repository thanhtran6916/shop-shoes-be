package com.adidas.shopshoes.repository;

import com.adidas.shopshoes.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, String> {

    Privilege findByName(String name);
}
