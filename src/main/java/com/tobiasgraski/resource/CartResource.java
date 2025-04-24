package com.tobiasgraski.resource;

import com.tobiasgraski.dao.CartDAO;
import com.tobiasgraski.model.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private static final CartDAO cartDAO = new CartDAO();

    @POST
    @Path("/items")
    public Response addToCart(@PathParam("customerId") int customerId, Book book) {
        cartDAO.addToCart(customerId, book);
        var cart = cartDAO.findByCustomerId(customerId);
        return Response.ok().entity(cart).build();
    }

    @GET
    @Path("/")
    public Response getCart(@PathParam("customerId") int customerId) {
        var cart = cartDAO.findByCustomerId(customerId);
        return Response.ok(cart).build();
    }

    @PUT
    @Path("/items/{bookId}")
    public Response updateBookInCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId, Book book) {
        book.setId(bookId);
        cartDAO.updateBookInCart(customerId, book);
        return Response.ok().build();
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response removeFromCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        cartDAO.removeFromCart(customerId, bookId);
        return Response.ok().build();
    }
}
