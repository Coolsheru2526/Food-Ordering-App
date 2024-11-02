package com.shreyansh.food_backend_springboot.service;

import com.shreyansh.food_backend_springboot.model.Cart;
import com.shreyansh.food_backend_springboot.model.CartItem;
import com.shreyansh.food_backend_springboot.model.Food;
import com.shreyansh.food_backend_springboot.model.User;
import com.shreyansh.food_backend_springboot.repository.CartItemRepository;
import com.shreyansh.food_backend_springboot.repository.CartRepository;
import com.shreyansh.food_backend_springboot.repository.FoodRepository;
import com.shreyansh.food_backend_springboot.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = findCartByUserId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)) {

                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setCart(cart);
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedItem=cartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);
        cartRepository.save(cart);

        return savedItem;

    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()) {
            throw new Exception("cart item not exist with id "+cartItemId);
        }
        cartItem.get().setQuantity(quantity);
        cartItem.get().setTotalPrice((cartItem.get().getFood().getPrice()*quantity));
        return cartItemRepository.save(cartItem.get());
    }


    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Cart cart = findCartByUserId(user.getId());

        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);

        if(cartItem.isEmpty()) {
            throw new Exception("cart item not exist with id "+cartItemId);
        }

        cart.getItems().remove(cartItem.get());
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total = 0L;
        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            return cart.get();
        }
        throw new Exception("Cart not found with the id "+id);
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

        Optional<Cart> opt=cartRepository.findByCustomerId(userId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("cart not found");

    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart=findCartByUserId(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
