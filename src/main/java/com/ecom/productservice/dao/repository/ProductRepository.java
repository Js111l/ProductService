package com.ecom.productservice.dao.repository;

import com.ecom.productservice.dao.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query(value = "SELECT p FROM Product p WHERE p.startDateOfSale <= CURRENT_DATE and p.endDateOfSale >= CURRENT_DATE")
  List<Product> getActiveProducts();
}
