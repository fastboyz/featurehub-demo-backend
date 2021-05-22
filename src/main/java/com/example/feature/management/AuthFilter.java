package com.example.feature.management;

import io.featurehub.client.ClientContext;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = Logger.getLogger("ContainerRequestFilter");

    @Inject
    javax.inject.Provider<ClientContext> contextProvider;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getHeaders().containsKey("Authorization")) {
            String user = requestContext.getHeaderString("Authorization");

            LOGGER.info(String.format("Incoming request from user %s", user));

            try {
                contextProvider.get().userKey(user).build().get();
            } catch (Exception e) {
                LOGGER.severe("Unable to set User key on user");
            }

        } else {
            LOGGER.info("Request has no User");
        }

    }
}
