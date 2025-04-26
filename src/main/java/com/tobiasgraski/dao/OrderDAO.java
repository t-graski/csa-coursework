package com.tobiasgraski.dao;

import com.tobiasgraski.exceptions.CartNotFoundException;
import com.tobiasgraski.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static final List<Order> orders = new ArrayList<>();
    private static final CartDAO cartDAO = new CartDAO();

    private static int currentId = 1;

    public List<Order> findByCustomerId(int customerId) {
        return orders.stream().filter(o -> o.getCart().getCustomer().getId() == customerId).toList();
    }

    public Order findById(int orderId) {
        return orders.stream().filter(o -> o.getCart().getId() == orderId).findFirst().orElseThrow(() ->
                new CartNotFoundException("No order found with Id " + orderId));
    }

    public Order create(int customerId) {
        var order = new Order();
        var cart = cartDAO.findByCustomerId(customerId);
        cart.getBooks().forEach(b -> b.setStock(b.getStock() - 1));
        order.setCart(cart);
        order.getCart().setId(getNextId());
        orders.add(order);
        return order;
    }

    private int getNextId() {
        return currentId++;
    }
}
