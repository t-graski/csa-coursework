package com.tobiasgraski.resource;

import com.tobiasgraski.dao.CustomerDAO;
import com.tobiasgraski.model.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private static final CustomerDAO customerDAO = new CustomerDAO();

    @POST
    @Path("/")
    public Response createCustomer(Customer customer) {
        var created = customerDAO.create(customer);
        return Response.created(URI.create("/customers/" + created.getId())).entity(created).build();
    }

    @GET
    @Path("/")
    public Response getCustomers() {
        var customers = customerDAO.findAll();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) {
        var customer = customerDAO.findById(id);
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customerDTO) {
        var updatedCustomer = customerDAO.update(id, customerDTO);
        return Response.ok(updatedCustomer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        customerDAO.delete(id);
        return Response.ok().build();
    }
}
