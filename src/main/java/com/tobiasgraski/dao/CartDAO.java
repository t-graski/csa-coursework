package com.tobiasgraski.dao;

import com.tobiasgraski.exceptions.CartNotFoundException;
import com.tobiasgraski.model.Book;
import com.tobiasgraski.model.Cart;
import com.tobiasgraski.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    private static final List<Cart> carts = new ArrayList<>();
    private static final BookDAO bookDAO = new BookDAO();
    private static final CustomerDAO customerDAO = new CustomerDAO();

    private static int currentId = 1;

    // find cart of customer
    public Cart findByCustomerId(int customerId) {
        return carts.stream().filter(c -> c.getCustomer().getId() == customerId).findFirst().orElseThrow(() -> new CartNotFoundException("No cart found for customer with Id " + customerId));
    }

    public void addToCart(int customerId, Book book) {
        var cart = carts.stream().filter(c -> c.getCustomer().getId() == customerId).findFirst().orElse(null);
        if (cart == null) {
            cart = new Cart(getNextId(), customerDAO.findById(customerId));
            carts.add(cart);
        }

        var existingBook = bookDAO.findById(book.getId());

        cart.getBooks().add(existingBook);
    }

    public void updateBookInCart(int customerId, Book book) {
        var cart = findByCustomerId(customerId);
        var existingBook = cart.getBooks().stream().filter(b -> b.getId() == book.getId()).findFirst().orElseThrow(() -> new CartNotFoundException("No book found in cart with Id " + book.getId()));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
    }

    public void removeFromCart(int customerId, int bookId) {
        var cart = findByCustomerId(customerId);
        cart.getBooks().removeIf(b -> b.getId() == bookId);
    }

    public int getNextId() {
        return currentId++;
    }
}
