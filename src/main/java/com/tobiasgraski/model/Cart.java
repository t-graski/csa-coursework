package com.tobiasgraski.model;

import java.util.List;

public class Cart {
    private Customer customer;
    private List<Book> books;

    public Cart(Customer customer) {
        this.customer = customer;
    }


}
