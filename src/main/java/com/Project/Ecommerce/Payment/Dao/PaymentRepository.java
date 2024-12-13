package com.Project.Ecommerce.Payment.Dao;

import com.Project.Ecommerce.Payment.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
