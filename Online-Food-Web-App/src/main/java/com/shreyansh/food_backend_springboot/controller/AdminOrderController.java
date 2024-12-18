package com.shreyansh.food_backend_springboot.controller;

import com.shreyansh.food_backend_springboot.model.Order;
import com.shreyansh.food_backend_springboot.service.OrderService;
import com.shreyansh.food_backend_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws Exception {
        if(orderId!=null) {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Order deleted with id)"+orderId);
        }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
    }


    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(
            @PathVariable Long restaurantId,
            @RequestParam(required = false) String order_status) throws Exception{

        List<Order> orders = orderService.getRestaurantsOrder(restaurantId,order_status);

        return ResponseEntity.ok(orders);

    }

    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(@PathVariable Long orderId,@PathVariable String orderStatus) throws Exception {

        Order orders = orderService.updateOrder(orderId, orderStatus);
        return ResponseEntity.ok(orders);

    }

}
