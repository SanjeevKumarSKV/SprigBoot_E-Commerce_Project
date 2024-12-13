package com.Project.Ecommerce.CATEGORIES.Model;


import com.Project.Ecommerce.USER.MODEL.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private String productDescription;

    private Double price;

    private Double gstPercentage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId",nullable = false)
    private Categories categories;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Transient
    private Long tempCategoryId;
}
