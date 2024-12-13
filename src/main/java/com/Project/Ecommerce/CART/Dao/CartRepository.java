package com.Project.Ecommerce.CART.Dao;

import com.Project.Ecommerce.CART.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Optional<Cart> findByUserId(Long id);
}
