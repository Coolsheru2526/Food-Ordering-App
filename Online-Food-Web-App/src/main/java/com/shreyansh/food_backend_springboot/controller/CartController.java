package com.shreyansh.food_backend_springboot.controller;

import com.shreyansh.food_backend_springboot.model.Cart;
import com.shreyansh.food_backend_springboot.model.CartItem;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.request.AddCartItemRequest;
import com.shreyansh.food_backend_springboot.request.UpdateCartItemRequest;
import com.shreyansh.food_backend_springboot.service.CartService;
import com.shreyansh.food_backend_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cart = cartService.addItemToCart(req, jwt);
        return ResponseEntity.ok(cart);

    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cart = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {

        Cart cart = cartService.removeItemFromCart(id, jwt);
        return ResponseEntity.ok(cart);

    }

    @GetMapping("/cart/total")
    public ResponseEntity<Double> calculateCartTotals(@RequestParam Long cartId,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserByJwtToken(jwt);

        Cart cart =cartService.findCartByUserId(user.getId());
        double total = cartService.calculateCartTotals(cart);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/cart/")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> cleareCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return ResponseEntity.ok(cart);
    }

}
