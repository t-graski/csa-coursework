package com.tobiasgraski.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {
    @Override
    public Response toResponse(CartNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
    }
}
