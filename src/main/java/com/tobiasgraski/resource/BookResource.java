package com.tobiasgraski.resource;

import com.tobiasgraski.dao.BookDAO;
import com.tobiasgraski.dto.BookDTO;
import com.tobiasgraski.model.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URISyntaxException;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookDAO bookDAO = new BookDAO();

    @POST
    @Path("/")
    public Response createBook(BookDTO book, @Context UriInfo uriInfo) {
        var created = bookDAO.create(book);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(created.getId()));
        return Response.created(uriBuilder.build()).entity(created).build();
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
    public Response updateBook(@PathParam("id") int id) {
        return Response.ok().build();
    }
}
