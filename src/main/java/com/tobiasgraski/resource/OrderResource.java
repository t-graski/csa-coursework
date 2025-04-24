package com.tobiasgraski.resource;

import com.tobiasgraski.dao.OrderDAO;
import com.tobiasgraski.model.Order;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.net.URI;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private static final OrderDAO orderDAO = new OrderDAO();

    @POST
    @Path("/")
    public Response createOrder(@PathParam("customerId") int customerId) {
        var created = orderDAO.create(customerId);
        return Response.created(URI.create("/customers/" + customerId + "/orders/" + created.getCart().getId())).entity(created).build();
    }

    @GET
    @Path("/")
    public Response getOrders(@PathParam("customerId") int customerId) {
        var orders = orderDAO.findByCustomerId(customerId);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        var order = orderDAO.findById(orderId);
        return Response.ok(order).build();
    }
}
