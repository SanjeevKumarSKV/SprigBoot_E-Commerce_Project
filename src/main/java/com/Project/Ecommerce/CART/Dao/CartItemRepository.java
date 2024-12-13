package com.Project.Ecommerce.CART.Dao;


import com.Project.Ecommerce.CART.Model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long integer);



    Optional<CartItem> findByCartIdAndProductId(Long id, Long tempProductId);


    @Modifying
    @Transactional
    @Query(value = "delete from cart_items where id = :cartItemId", nativeQuery = true)
    void deleteOrderItemById(Long cartItemId);
}
