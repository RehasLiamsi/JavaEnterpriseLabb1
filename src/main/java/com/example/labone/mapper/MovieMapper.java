package com.example.labone.mapper;

import com.example.labone.dto.MovieDto;
import com.example.labone.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MovieMapper {
    public List<MovieDto> map(List<Movie> all) {
        return all.stream().map(movie -> new MovieDto(movie.getId(), movie.getName(), movie.getGenre())).toList();
    }
}
