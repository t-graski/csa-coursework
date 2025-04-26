package com.tobiasgraski.dao;

import com.tobiasgraski.exceptions.BookNotFoundException;
import com.tobiasgraski.exceptions.CartNotFoundException;
import com.tobiasgraski.exceptions.OutOfStockException;
import com.tobiasgraski.model.Book;
import com.tobiasgraski.model.Cart;
import com.tobiasgraski.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    private static final List<Cart> carts = new ArrayList<>();
    private static final BookDAO bookDAO = new BookDAO();
    private static final AuthorDAO authorDAO = new AuthorDAO();
    private static final CustomerDAO customerDAO = new CustomerDAO();

    private static int currentId = 1;

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

        if (existingBook.getStock() <= 0) {
            throw new OutOfStockException("No book with Id " + book.getId() + " in stock");
        }

        cart.getBooks().add(existingBook);
    }

    public void updateBookInCart(int customerId, Book book) {
        var cart = findByCustomerId(customerId);
        var existingBook = cart.getBooks().stream().filter(b -> b.getId() == book.getId()).findFirst().orElseThrow(() -> new CartNotFoundException("No book found in cart with Id " + book.getId()));
        var existingAuthor = authorDAO.findById(book.getAuthorId());

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(existingAuthor);
        existingBook.setPrice(book.getPrice());
    }

    public void removeFromCart(int customerId, int bookId) {
        var cart = findByCustomerId(customerId);
        var success = cart.getBooks().removeIf(b -> b.getId() == bookId);
        if (!success)
            throw new BookNotFoundException("No book found with in cart with Id " + bookId);
    }

    public int getNextId() {
        return currentId++;
    }
}
