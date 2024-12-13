package com.Project.Ecommerce.CART.Model;


import com.Project.Ecommerce.CATEGORIES.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cartItem")
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double productPrice;

    private Double gstAmount;

    private Double itemTotal;

    private  int quantity;
    @JsonIgnore
    @ManyToOne(cascade =CascadeType.REMOVE)
    @JoinColumn(name = "cartId")
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

   @Transient
    private Long tempProductId;

}

