package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.*;
import com.shreyansh.food_backend_springboot.repository.*;
import com.shreyansh.food_backend_springboot.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {


        Address shippAddress = order.getDeliveryAddress();


        Address savedAddress = addressRepository.save(shippAddress);

        if(!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);

        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder = new Order();

        createdOrder.setCustomer(user);
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getFood().getPrice()* cartItem.getQuantity());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createdOrder.setTotalAmount(totalPrice);
        createdOrder.setRestaurant(restaurant);

        createdOrder.setItems(orderItems);
        Order savedOrder = orderRepository.save(createdOrder);

        restaurant.getOrders().add(savedOrder);

        restaurantRepository.save(restaurant);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order=findOrderById(orderId);

        if(orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        else throw new Exception("Please Select A Valid Order Status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        if(order==null) {
            throw new Exception("Order not found with the id "+orderId);
        }

        orderRepository.deleteById(orderId);
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) return order.get();

        throw new Exception("Order not found with the id "+orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

        if(orderStatus!=null) {
            orders = orders.stream()
                    .filter(order->order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }

        if(orders.isEmpty()) throw new Exception(("Order with status not found"));
        return orders;
    }
}