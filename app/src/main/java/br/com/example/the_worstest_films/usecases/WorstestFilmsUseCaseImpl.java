package br.com.example.the_worstest_films.usecases;

import br.com.example.the_worstest_films.models.dto.ProducerDTO;
import br.com.example.the_worstest_films.models.dto.WorstestFilmsProducersDTO;
import br.com.example.the_worstest_films.models.entities.Movie;
import br.com.example.the_worstest_films.ports.input.WorstestFilmsUseCase;
import br.com.example.the_worstest_films.ports.output.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WorstestFilmsUseCaseImpl implements WorstestFilmsUseCase {

    public static final Logger log = LoggerFactory.getLogger(WorstestFilmsUseCaseImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Método para obter os produtores com os maiores e menores intervalos entre prêmios.
     *
     * @return Um objeto WorstestFilmsProducersDTO contendo listas de produtores com os menores e maiores intervalos.
     */
    public WorstestFilmsProducersDTO worstestFilmsProducers() {

        Map<Integer, List<ProducerDTO>> mapProducers = new java.util.TreeMap<>();
        Integer minInterval = null, maxInterval = null;

        log.info("getting producers...");
        List<String> producers = getProducers();
        if (producers.isEmpty())
            return new WorstestFilmsProducersDTO(null, null);

        for (String pr : producers) {

            log.info("getting movies by producer: {} ", pr);
            var producerDTO = getMovieIntervalByProducer(pr);
            if (producerDTO == null)
                continue;

            int interval = producerDTO.interval();
            if (interval > 0) {

                if (minInterval == null || interval < minInterval) {
                    minInterval = interval;
                }
                if (maxInterval == null || interval > maxInterval) {
                    maxInterval = interval;
                }

                mapProducers.computeIfAbsent(interval, k -> new ArrayList<>()).add(producerDTO);
            }
        }

        log.info("defining min and max producers...");
        var min = mapProducers.get(minInterval);
        var max = mapProducers.get(maxInterval);

        return new WorstestFilmsProducersDTO(min, max);
    }

    /**
     * Método para obter uma lista de produtores distintos que ganharam prêmios.
     *
     * @return Uma lista de nomes de produtores distintos.
     */
    private List<String> getProducers() {

        //Esse código poderia ser reduzido/suprimido com um banco de dados mais robusto
        List<String> splitedProducers = new ArrayList<>();
        movieRepository.findWinnersProducers().forEach(p ->
                splitedProducers.addAll(List.of(p.split(", | and ")))
        );
        List<String> distinctProducers = splitedProducers.stream().distinct().toList();

        log.info("producers: {} ", distinctProducers);

        return distinctProducers;
    }


    /**
     * Método para obter o intervalo entre prêmios de um produtor específico.
     *
     * @param producer O nome do produtor.
     * @return Um objeto ProducerDTO contendo o nome do produtor, intervalo entre prêmios, ano do prêmio anterior e ano do prêmio seguinte.
     */
    private ProducerDTO getMovieIntervalByProducer(String producer) {

        List<Movie> movies = movieRepository.findByProducerContainingOrderByMovieYear(producer);
        if (movies.isEmpty() || movies.size() == 1)
            return null;

        Integer minYear = movies.getFirst().getMovieYear();
        Integer maxYear = movies.getLast().getMovieYear();
        int interval = maxYear - minYear;

        return new ProducerDTO(producer, interval, minYear, maxYear);
    }
}