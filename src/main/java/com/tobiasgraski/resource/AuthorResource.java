package com.tobiasgraski.resource;

import com.tobiasgraski.dao.AuthorDAO;
import com.tobiasgraski.model.Author;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private static final AuthorDAO authorDAO = new AuthorDAO();

    @POST
    @Path("/")
    public Response createAuthor(Author authorDTO) {
        var created = authorDAO.create(authorDTO);
        return Response.created(URI.create("/authors/" + created.getId())).entity(created).build();
    }

    @GET
    @Path("/")
    public Response getAuthors() {
        var authors = authorDAO.findAll();
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) {
        var author = authorDAO.findById(id);
        return Response.ok(author).build();
    }

    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        var books = authorDAO.findBooksByAuthorId(id);
        return Response.ok(books).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author authorDTO) {
        var updatedAuthor = authorDAO.update(id, authorDTO);
        return Response.ok(updatedAuthor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        authorDAO.delete(id);
        return Response.ok().build();
    }
}
