package com.example.feature.management;

import io.featurehub.client.ClientContext;
import io.featurehub.client.EdgeFeatureHubConfig;
import io.featurehub.client.FeatureHubConfig;
import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@ApplicationScoped
public class FeatureSource {
    private static final Logger LOGGER = Logger.getLogger("FeatureService");
    @ConfigProperty(name = "feature-hub.url")
    String url;
    @ConfigProperty(name = "feature-hub.api-key")
    String apiKey;

    /**
     * THe FeatureHubConfig bean must be available for all uses
     *
     * @return FeatureHubConfig - the config ready for use
     */
    @Startup
    @Produces
    @ApplicationScoped
    public FeatureHubConfig fhConfig() {
        final EdgeFeatureHubConfig config = new EdgeFeatureHubConfig(url, apiKey);
        config.init();
        LOGGER.info("FeatureHub started");
        return config;
    }
    /**
     * This lets us create the client context, which will be always empty, or the AuthFilter will add the user if it
     * discovers it.
     * @param config - The FeatureHub Config
     * @return - A blank context usable by any resource.
     */
    @Produces
    @RequestScoped
    public ClientContext fhClient(FeatureHubConfig config) {
        try {
            return config.newContext().build().get();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Cannot create context!", e);
            throw new RuntimeException(e);
        }
    }
}
