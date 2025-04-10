package com.tobiasgraski.dao;

import com.tobiasgraski.dto.BookDTO;
import com.tobiasgraski.exceptions.BookNotFoundException;
import com.tobiasgraski.model.Author;
import com.tobiasgraski.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAO {

    private static AuthorDAO authorDAO = new AuthorDAO();
    private static final List<Book> books = new ArrayList<>();
    private static int currentId = 1;

    public List<Book> findAll() {
        return books;
    }

    public Book findById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElseThrow(() -> new BookNotFoundException("No book with Id " + id));
    }

    public Book create(BookDTO bookDTO) {
        var author = authorDAO.findById(bookDTO.authorId());
        var book = new Book(getNextId(), bookDTO.title(), author, bookDTO.isbn(), bookDTO.publicationYear(), bookDTO.price(), bookDTO.stock());
        books.add(book);
        return book;
    }

    public Book update(int id, BookDTO bookDTO) {
        var book = findById(id);
        book.setTitle(bookDTO.title());
        book.setAuthor(authorDAO.findById(bookDTO.authorId()));
        book.setISBN(bookDTO.isbn());
        book.setPublicationYear(bookDTO.publicationYear());
        book.setPrice(bookDTO.price());
        book.setStock(bookDTO.stock());
        return book;
    }

    public void delete(int id) {
        books.removeIf(b -> b.getId() == id);
    }

    private int getNextId() {
        return currentId++;
    }
}
