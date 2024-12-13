package com.Project.Ecommerce.Order.Service;

import com.Project.Ecommerce.CART.Model.Cart;
import com.Project.Ecommerce.CART.Model.CartItem;
import com.Project.Ecommerce.Order.Dao.OrderItemRepository;
import com.Project.Ecommerce.Order.Dao.OrderRepository;
import com.Project.Ecommerce.Order.Model.EOrderStatus;
import com.Project.Ecommerce.Order.Model.Order;
import com.Project.Ecommerce.Order.Model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public Order createOrder(Cart cart) {
        Order order = Order.builder().orderStatus(EOrderStatus.PENDING).orderTotal(cart.getTotalPrice())
                .gstAmount(cart.getGstAmount())
                .totalAmountWithGST(cart.getTotalAmountWithGst()).user(cart.getUser()).build();

        orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem items : cart.getCartItems()) {
            OrderItem orderItem = OrderItem.builder().order(order).itemTotal(items.getItemTotal()).product(items.getProduct())
                    .quantity(items.getQuantity()).gstAmount(items.getGstAmount())
                    .productPrice(items.getProductPrice())
                    .build();

            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);
        return order;

    }

    public void saveOrderItems(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);

    }

    public Order findOrder(Long userId, Long orderId) throws Exception {
        Optional<Order> maybeOrder = orderRepository.findByUserIdAndId(userId, orderId);

        if(maybeOrder.isPresent()){
            return maybeOrder.get();
        }else{
            throw new Exception("Order not found");
        }

    }

    public void completeOrder(Order order){
        order.setOrderStatus(EOrderStatus.COMPLETED);
        orderRepository.save(order);
    }

}
