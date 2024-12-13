package com.Project.Ecommerce.CART.Service;


import com.Project.Ecommerce.CART.Dao.CartItemRepository;
import com.Project.Ecommerce.CART.Dao.CartRepository;
import com.Project.Ecommerce.CART.Model.Cart;
import com.Project.Ecommerce.CART.Model.CartDTO;
import com.Project.Ecommerce.CART.Model.CartItem;
import com.Project.Ecommerce.CATEGORIES.Dao.ProductRepository;
import com.Project.Ecommerce.CATEGORIES.Model.Product;
import com.Project.Ecommerce.Configuration.UserContext;
import com.Project.Ecommerce.Order.Model.Order;
import com.Project.Ecommerce.Order.Service.OrderService;
import com.Project.Ecommerce.USER.DAO.UserRepository;
import com.Project.Ecommerce.USER.MODEL.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new RuntimeException("Cart Not Found"));
    }

    @Transactional
    public void addToCart(CartDTO cartDTO) throws Exception {
        Long id = UserContext.getUserId();
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User Not Found"));
        Cart cart = cartRepository.findById(id).orElse(new Cart());
        cart.setUser(user);
        cartRepository.save(cart);
        Product product = productRepository.findById(cartDTO.getCartItem().getTempProductId()).orElseThrow(() -> new Exception("Product Dose Not Exist "));

        int quantity = cartDTO.getCartItem().getQuantity();
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setQuantity(quantity);
        cartItem.setProduct(product);
        cartItem.setProductPrice(product.getPrice());

        calculateTotal(cartItem, cart, product);

        cartRepository.save(cart);

    }

    private void calculateTotal(CartItem cartItem, Cart cart, Product product) {
        double itemTotal = cartItem.getQuantity() * product.getPrice();
        double gstAmount = itemTotal * (product.getGstPercentage() / 100);
        cartItem.setItemTotal(itemTotal);
        cartItem.setGstAmount(gstAmount);

        cartItemRepository.save(cartItem);

        List<CartItem> cartItemList = cartItemRepository.findByCartId(cart.getId());

        double finalTotal = cartItemList.stream().mapToDouble(item -> item.getItemTotal()).sum();
        double gstTotal = cartItemList.stream().mapToDouble(item -> item.getGstAmount()).sum();
        double totalWithGst = finalTotal + gstTotal;

        cart.setTotalPrice(finalTotal);
        cart.setGstAmount(gstAmount);
        cart.setTotalAmountWithGst(totalWithGst);

    }

    public Cart updateCart(Long Id, CartItem cartItem) throws Exception {
        Cart cart = getCartById(Id);

        CartItem existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(),
                cartItem.getTempProductId()).orElseThrow(() -> new Exception("cart item not found"));

        Product product = productRepository.findById(cartItem.getTempProductId())
                .orElseThrow(() -> new Exception("Product not found"));

        existingItem.setQuantity(cartItem.getQuantity());
        existingItem.setProductPrice(product.getPrice());

        calculateTotal(existingItem, cart, product);

        return cartRepository.save(cart);
    }

    @Transactional
    public void createOrder(Long id) throws Exception {


        Cart cart = cartRepository.findByUserId(id).orElseThrow(() -> new Exception("User does not exists"));

        Order order = orderService.createOrder(cart);

        cart.setId(order.getId());

        cartRepository.save(cart);

        System.out.println("order completed successfully");
    }
@Transactional
public void deleteCartItem(Long id) throws Exception {
    CartItem cartItem = cartItemRepository.findById(id).orElseThrow( () -> new Exception("Cart Item Not Found"));

    Cart cart = cartRepository.findById(cartItem.getCart().getId()).orElseThrow( () -> new Exception("Cart Not Found"));

    cartItemRepository.deleteOrderItemById(id);

    System.out.println("Cart item deleted successfully");

    List<CartItem> existingItems = cartItemRepository.findByCartId(cart.getId());

    double finalTotal = existingItems.stream().mapToDouble(item -> item.getItemTotal()).sum();
    double gstTotal = existingItems.stream().mapToDouble(item -> item.getGstAmount()).sum();
    double finalTotalWithGst = finalTotal + gstTotal;

    cart.setTotalPrice(finalTotal);
    cart.setGstAmount(gstTotal);
    cart.setTotalAmountWithGst(finalTotalWithGst);
    cartRepository.save(cart);

}

    public void deleteCart(Long id) throws Exception {
        Cart cart =  cartRepository.findByUserId(id).orElseThrow(() -> new Exception("Cart Not Found"));
        cartRepository.delete(cart);
    }


}