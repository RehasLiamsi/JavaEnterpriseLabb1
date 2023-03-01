package com.example.labone.controller;

import com.example.labone.dto.MovieDto;
import com.example.labone.entity.Movie;
import com.example.labone.mapper.MovieMapper;
import com.example.labone.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/movies")
public class MovieController {

    @Inject
    MovieRepository repository;

    @Inject
    MovieMapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getAll(@QueryParam("name") String name) {
        if(name == null)
            return mapper.map(repository.getAll());
        else return mapper.map(repository.getByName(name));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        var movie =repository.getById(id);
        if(movie.isPresent())
            return Response.status(200).entity(movie.get()).build();
        else return Response.status(404).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(@Valid Movie movie){
        repository.addMovie(movie);
        return Response.created(URI.create("movies/" + movie.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteOne(@PathParam("id") Long id){
        repository.deleteMovie(id);
    }
}
