package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.Order;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId,String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long orderId) throws Exception;

    public List<Order> getRestaurantsOrder(Long restaurantId,String orderStatus) throws Exception;
}
