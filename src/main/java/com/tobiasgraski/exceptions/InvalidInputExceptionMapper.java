package com.tobiasgraski.exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    @Override
    public Response toResponse(InvalidInputException e) {
        var error = new ErrorModel("Invalid input", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}
