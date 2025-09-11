package br.com.example.the_worstest_films.adapters.output.repositories;

import br.com.example.the_worstest_films.models.entities.Movie;
import br.com.example.the_worstest_films.ports.output.MovieRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepositoryImpl extends MovieRepository, JpaRepository<Movie, Long> {

    List<Movie> findByProducerContainingOrderByMovieYear(String producer);

    @Query(value = " SELECT DISTINCT m.producer FROM Movie m WHERE m.winner = true ORDER BY 1")
    List<String> findWinnersProducers();
}
