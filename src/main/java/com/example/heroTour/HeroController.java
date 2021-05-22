package com.example.heroTour;

import io.featurehub.client.ClientContext;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/hero")
public class HeroController {
    private final Provider<ClientContext> contextProvider;

    @Inject
    public HeroController(Provider<ClientContext> contextProvider) {
        this.contextProvider = contextProvider;
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") Long id) {
        Hero hero = Hero.findById(id);
        if (hero == null) {
            throw new WebApplicationException(404);
        }
        return Response.ok(hero).build();
    }

    @GET
    @Produces("application/json")
    public Response list() {
        return Response.ok(Hero.findAll().list()).build();
    }

    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Hero heroToSave) {
        Hero hero = new Hero();
        hero.setName(heroToSave.getName());
        hero.setVotes(heroToSave.getVotes());
        hero.persist();
        return Response.ok(hero).build();
    }

    @Transactional
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") Long id, Hero heroToUpdate) {
        Hero hero = Hero.findById(id);
        if (hero == null) {
            throw new WebApplicationException(404);
        }
        hero.setName(heroToUpdate.getName());
        hero.persist();

        return Response.ok(hero).build();

    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        Hero hero = Hero.findById(id);
        if (hero == null) {
            throw new WebApplicationException(404);
        }
        hero.delete();
    }
}
