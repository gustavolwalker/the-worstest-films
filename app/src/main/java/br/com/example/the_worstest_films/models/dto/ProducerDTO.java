package br.com.example.the_worstest_films.models.dto;

public record ProducerDTO(
        String producer,
        Integer interval,
        Integer previousWin,
        Integer followingWin) {
}
