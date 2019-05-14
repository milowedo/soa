package com.milosz.rest;

import com.milosz.entity.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class MovieRestService {

    @GET
    @Path("/movies")
    public Response getMovies() {
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Path("/movie/title")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieByTitle(@QueryParam("title") String title) {
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Path("/movie/{id}")
    public Response getMovie(@PathParam("id") String id) {
        return Response.status(200).entity("hello").build();
    }

    @PUT
    @Path("/movie/{id}/updateUri")
    public Response updateMovie(@PathParam("id") String id, String uri) {
        return Response.status(200).build();
    }

    @POST
    @Path("/movie")
    public Response addMovie(Movie movie ){
        return Response.status(200).build();
    }

    @DELETE
    @Path("/movie/{id}")
    public Response removeMovie(@PathParam("id") String id){
        return Response.status(200).build();
    }

}