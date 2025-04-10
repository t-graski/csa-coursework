package com.tobiasgraski.dao;

import com.tobiasgraski.dto.AuthorDTO;
import com.tobiasgraski.exceptions.AuthorNotFoundException;
import com.tobiasgraski.model.Author;
import com.tobiasgraski.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorDAO {
    private static BookDAO bookDAO = new BookDAO();
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

    public Author create(AuthorDTO authorDTO) {
        var author = new Author(getNextId(), authorDTO.firstName(), authorDTO.lastName(), authorDTO.biography());
        authors.add(author);
        return author;
    }

    public Author update(int id, AuthorDTO authorDTO) {
        var author = findById(id);
        author.setFirstName(authorDTO.firstName());
        author.setLastName(authorDTO.lastName());
        author.setBiography(authorDTO.biography());
        return author;
    }

    public void delete(int id) {
        authors.removeIf(a -> a.getId() == id);
    }

    private int getNextId() {
        return currentId++;
    }
}
