package com.Project.Ecommerce.CART.Web;

import com.Project.Ecommerce.CART.Model.Cart;
import com.Project.Ecommerce.CART.Model.CartDTO;
import com.Project.Ecommerce.CART.Model.CartItem;
import com.Project.Ecommerce.CART.Service.CartService;
import com.Project.Ecommerce.Configuration.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public String addToCart(@RequestBody CartDTO cartDTO) throws Exception {
        try {
            cartService.addToCart(cartDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "User does not exist", e);
        }

        return "success";
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) throws Exception {
        return cartService.getCartById(id);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody CartItem cartItem) throws Exception {
        return cartService.updateCart(id, cartItem);
    }

    @DeleteMapping("/item/{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId) throws Exception {
        cartService.deleteCart(cartItemId);
    }

    @PostMapping("/checkout")
    public String createOrder() throws Exception {
        Long userId = UserContext.getUserId();
        cartService.createOrder(userId);
        return "success";


    }


}
