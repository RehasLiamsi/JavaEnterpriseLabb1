package com.example.labone.repository;

import com.example.labone.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class MovieRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Movie> getAll() {
        var query = entityManager.createQuery("select  m from Movie m");
        return (List<Movie>) query.getResultList();
    }

    public List<Movie> getByName(String name){
        var query = entityManager.createQuery("select m from Movie m where m.name like :name");
        query.setParameter("name", name);
        return (List<Movie>) query.getResultList();
    }

    public Optional<Movie> getById(Long id){
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    public void addMovie(Movie movie){
        entityManager.persist(movie);
    }

    public void deleteMovie(Long id){
        var movie = getById(id);
        movie.ifPresent((m)-> entityManager.remove(m));
    }


}
