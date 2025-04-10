package com.tobiasgraski.dao;

import com.tobiasgraski.exceptions.BookNotFoundException;
import com.tobiasgraski.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private static final AuthorDAO authorDAO = new AuthorDAO();
    private static final List<Book> books = new ArrayList<>();
    private static int currentId = 1;

    public List<Book> findAll() {
        return books;
    }

    public Book findById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElseThrow(() -> new BookNotFoundException("No book with Id " + id));
    }

    public Book create(Book bookDTO) {
        var author = authorDAO.findById(bookDTO.getAuthorId());
        var book = new Book(getNextId(), bookDTO.getTitle(), author, bookDTO.getISBN(), bookDTO.getPublicationYear(), bookDTO.getPrice(), bookDTO.getStock());
        books.add(book);
        return book;
    }

    public Book update(int id, Book bookDTO) {
        var book = findById(id);
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(authorDAO.findById(bookDTO.getAuthorId()));
        book.setISBN(bookDTO.getISBN());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setPrice(bookDTO.getPrice());
        book.setStock(bookDTO.getStock());
        return book;
    }

    public void delete(int id) {
        books.removeIf(b -> b.getId() == id);
    }

    private int getNextId() {
        return currentId++;
    }
}
