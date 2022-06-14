package com.blogafac.kocirfan.repository;

import com.blogafac.kocirfan.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository<Product,Long >{

}
