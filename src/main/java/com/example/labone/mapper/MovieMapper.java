package com.example.labone.mapper;

import com.example.labone.dto.MovieDto;
import com.example.labone.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MovieMapper {
    public List<MovieDto> map(List<Movie> all) {
        return all.stream().map(MovieDto::new).toList();
    }

    public Movie map(MovieDto movie) {
        var m = new Movie();
        m.setId(movie.getId());
        m.setName(movie.getName());
        m.setGenre(m.getGenre());
        return m;
    }

    public MovieDto map(Movie movie){
        return new MovieDto(movie);
    }

}
