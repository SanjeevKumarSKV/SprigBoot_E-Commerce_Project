package com.Project.Ecommerce.CART.Model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    Cart cart;

    CartItem cartItem;
}
