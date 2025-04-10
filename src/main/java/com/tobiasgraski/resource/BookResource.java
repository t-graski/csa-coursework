package com.tobiasgraski.resource;

import com.tobiasgraski.dao.BookDAO;
import com.tobiasgraski.model.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private static final BookDAO bookDAO = new BookDAO();

    @POST
    @Path("/")
    public Response createBook(Book book) {
        var created = bookDAO.create(book);
        return Response.created(URI.create("/books/" + created.getId())).entity(created).build();
    }

    @GET
    @Path("/")
    public Response getBooks() {
        var books = bookDAO.findAll();
        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") int id) {
        var book = bookDAO.findById(id);
        return Response.ok(book).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book bookDTO) {
        var updatedBook = bookDAO.update(id, bookDTO);
        return Response.ok(updatedBook).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        bookDAO.delete(id);
        return Response.ok().build();
    }
}
