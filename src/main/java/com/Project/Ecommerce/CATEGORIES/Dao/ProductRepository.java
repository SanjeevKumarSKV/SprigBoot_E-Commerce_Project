package com.Project.Ecommerce.CATEGORIES.Dao;


import com.Project.Ecommerce.CART.Model.CartItem;
import com.Project.Ecommerce.CATEGORIES.Model.Product;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.OptionalDouble;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
