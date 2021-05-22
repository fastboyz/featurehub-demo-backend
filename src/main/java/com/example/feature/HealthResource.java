package com.example.feature;

import io.featurehub.client.FeatureHubConfig;
import io.featurehub.client.Readyness;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health/liveliness")
public class HealthResource {
    private final FeatureHubConfig config;

    @Inject
    public HealthResource(FeatureHubConfig config) {
        this.config = config;
    }

    @GET
    public Response liveliness() {
        if (config.getReadyness() == Readyness.Ready) {
            return Response.ok().build();
        }

        return Response.status(503).build();
    }
}
