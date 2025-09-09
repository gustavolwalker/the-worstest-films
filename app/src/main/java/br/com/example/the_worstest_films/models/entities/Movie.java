package br.com.example.the_worstest_films.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String studios;
    private String producer;
    private Integer movieYear;

    private Boolean winner;

    public Movie() {
    }

    public Movie(String title, String studios, String producer, Integer movieYear, Boolean winner) {
        this.title = title;
        this.studios = studios;
        this.producer = producer;
        this.movieYear = movieYear;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStudios() {
        return studios;
    }

    public String getProducer() {
        return producer;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public Boolean getWinner() {
        return winner;
    }
}