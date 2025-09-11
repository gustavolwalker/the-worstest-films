package br.com.example.the_worstest_films.adapters.input.controllers;

import br.com.example.the_worstest_films.models.dto.WorstestFilmsProducersDTO;
import br.com.example.the_worstest_films.ports.input.WorstestFilmsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worstest-films")
public class WortestsFilmsController {

    @Autowired
    private WorstestFilmsUseCase worstestFilmsUseCase;

    @GetMapping("/v1/producers")
    public ResponseEntity<WorstestFilmsProducersDTO> getProducers() {

        return ResponseEntity.ok(worstestFilmsUseCase.worstestFilmsProducers());
    }
}
