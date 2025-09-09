package br.com.example.the_worstest_films.adapters.output.repositories;

import br.com.example.the_worstest_films.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


}
