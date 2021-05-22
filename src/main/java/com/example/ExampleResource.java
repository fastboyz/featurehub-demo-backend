package com.example;

import io.featurehub.client.ClientContext;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {
    private final Provider<ClientContext> contextProvider;

    @Inject
    public ExampleResource(Provider<ClientContext> contextProvider) {
        this.contextProvider = contextProvider;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello world! " + contextProvider.get().feature("SUBMIT_COLOR_BUTTON").getString();
    }
}