package com.Project.Ecommerce.Payment.Model;

import com.Project.Ecommerce.Order.Model.Order;
import com.Project.Ecommerce.USER.MODEL.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "payments")

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    private String paymentMethod;

    private Double totalAmountWithGST ;


}
