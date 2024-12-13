package com.Project.Ecommerce.ADDRESS.Model;

import com.Project.Ecommerce.USER.MODEL.User;
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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String addressType;

    private int doorNo;

    private String streetName;

    private String landMark;

    private int postalCode;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
