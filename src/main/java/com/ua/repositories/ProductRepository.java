package com.ua.repositories;

import com.ua.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository <T extends Product> extends JpaRepository<T, Long> {

}
