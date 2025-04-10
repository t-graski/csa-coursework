package com.tobiasgraski.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private Customer customer;
    private List<Book> books;

    public Cart() {

    }

    public Cart(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.books = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
