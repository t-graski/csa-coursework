package com.tobiasgraski.dao;

import com.tobiasgraski.exceptions.AuthorNotFoundException;
import com.tobiasgraski.exceptions.InvalidInputException;
import com.tobiasgraski.model.Author;
import com.tobiasgraski.model.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private static final BookDAO bookDAO = new BookDAO();
    private static final List<Author> authors = new ArrayList<>();
    private static int currentId = 1;

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(int id) {
        return authors.stream().filter(a -> a.getId() == id).findFirst().orElseThrow(() -> new AuthorNotFoundException("No author with Id " + id));
    }

    public List<Book> findBooksByAuthorId(int id) {
        if (findById(id) == null) throw new AuthorNotFoundException("No author with Id " + id);
        return bookDAO.findAll().stream().filter(b -> b.getAuthor().getId() == id).toList();
    }

    public Author create(Author authorDTO) {
        var author = new Author(getNextId(), authorDTO.getFirstName(), authorDTO.getLastName(), authorDTO.getBiography());
        authors.add(author);
        return author;
    }

    public Author update(int id, Author authorDTO) {
        var author = findById(id);
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setBiography(authorDTO.getBiography());
        return author;
    }

    public void delete(int id) {
        var success = authors.removeIf(a -> a.getId() == id);
        if (!success) throw new AuthorNotFoundException("No author with Id " + id);
    }

    private int getNextId() {
        return currentId++;
    }
}
