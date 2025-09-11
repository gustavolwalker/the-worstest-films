package br.com.example.the_worstest_films.models.dto;


import java.util.List;

public record WorstestFilmsProducersDTO(
        List<ProducerDTO> min,
        List<ProducerDTO> max
) {
}
