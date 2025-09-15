package br.com.example.the_worstest_films.ports.output;

import br.com.example.the_worstest_films.models.entities.Movie;

import java.util.List;

public interface MovieRepository {

    Movie save(Movie entity);

    List<Movie> findByWinnerTrueAndProducerContainingOrderByMovieYear(String producer);

    List<String> findWinnersProducers();
}


