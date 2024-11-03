package com.shreyansh.food_backend_springboot.controller;

import com.shreyansh.food_backend_springboot.model.Order;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.request.OrderRequest;
import com.shreyansh.food_backend_springboot.service.OrderService;
import com.shreyansh.food_backend_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order>  createOrder(@RequestBody OrderRequest req,
                                                        @RequestHeader("Authorization") String jwt)
            throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req,user);

        return new ResponseEntity<>(order,HttpStatus.CREATED);

    }



    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders(@RequestHeader("Authorization") String jwt) throws Exception{

        User user=userService.findUserByJwtToken(jwt);

        List<Order> userOrders = orderService.getUsersOrder(user.getId());
        return ResponseEntity.ok(userOrders);
    }

}