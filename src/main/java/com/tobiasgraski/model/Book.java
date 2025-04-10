package com.tobiasgraski.model;

import com.tobiasgraski.exceptions.InvalidInputException;

import java.time.Year;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private Author author;
    private String ISBN;
    private int publicationYear;
    private double price;
    private int stock;

    public Book() {
    }

    public Book(int id, String title, Author author, String ISBN, int publicationYear, double price, int stock) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setISBN(ISBN);
        setPublicationYear(publicationYear);
        setPrice(price);
        setStock(stock);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) throw new InvalidInputException("Title cannot be empty");
        if (title.length() > 100) throw new InvalidInputException("Title cannot be longer than 100 characters");
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        if (ISBN.isEmpty()) throw new InvalidInputException("ISBN cannot be empty");
        this.ISBN = ISBN;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear > Year.now().getValue())
            throw new InvalidInputException("Publication year cannot be in the future");
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) throw new InvalidInputException("Price cannot be 0 or negative");
        this.price = price;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new InvalidInputException("Stock cannot be negative");
        this.stock = stock;
    }
}
