package com.Project.Ecommerce.Payment.Service;

import com.Project.Ecommerce.CART.Service.CartService;
import com.Project.Ecommerce.Order.Model.Order;
import com.Project.Ecommerce.Order.Service.OrderService;
import com.Project.Ecommerce.Payment.Dao.PaymentRepository;
import com.Project.Ecommerce.Payment.Model.Payment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    public String completePayment(Long userId, Long orderId, boolean fail) throws Exception {

        Order order = orderService.findOrder(userId, orderId);

        Payment payments = Payment.builder()
                .paymentMethod("VISA")
                .transactionId(String.valueOf(Math.random()))
                .totalAmountWithGST(order.getTotalAmountWithGST()).user(order.getUser())
                .order(order).build();

        paymentRepository.save(payments);

        orderService.completeOrder(order);

        if(fail)
            throw new Exception("Payment failed");

        cartService.deleteCart(userId);


        return "Payment Success";
    }

}
