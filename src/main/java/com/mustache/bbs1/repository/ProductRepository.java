package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
