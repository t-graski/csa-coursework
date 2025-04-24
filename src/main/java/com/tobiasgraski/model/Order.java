package com.tobiasgraski.model;

public class Order {
    private Cart cart;

    public Order() {
    }

    public Order(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
