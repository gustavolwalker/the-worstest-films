package br.com.example.the_worstest_films.configurations;

import br.com.example.the_worstest_films.adapters.output.repositories.MovieRepository;
import br.com.example.the_worstest_films.models.entities.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

@Configuration
public class MoviesLoaderConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MoviesLoaderConfiguration.class);

    @Bean
    public CommandLineRunner loadMovies(MovieRepository movieRepository) {
        return args -> {

            String filePath = "/movielist.csv";

            try (Reader in = new InputStreamReader(
                    Objects.requireNonNull(getClass().getResourceAsStream(filePath)))) {

                CSVParser parser = CSVFormat.DEFAULT.builder()
                        .setDelimiter(';')
                        .setHeader()
                        .get()
                        .parse(in);

                for (CSVRecord record : parser) {

                    Movie movie = new Movie(
                            record.get("title"),
                            record.get("studios"),
                            record.get("producers"),
                            Integer.parseInt(record.get("year")),
                            Boolean.parseBoolean(record.get("winner")));

                    movieRepository.save(movie);
                }
            } catch (IOException e) {
                log.error("Error to load movielist", e);
            }
        };
    }
}
