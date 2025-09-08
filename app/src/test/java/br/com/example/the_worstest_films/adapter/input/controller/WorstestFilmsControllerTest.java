package br.com.example.the_worstest_films.adapter.input.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WorstestFilmsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTheWorstFilms() throws Exception {
        mockMvc.perform(get("/worstest-films/v1/producers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min", hasSize(2)))
                .andExpect(jsonPath("$.min[0].producer").value("Producer 1"))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.min[0].previousWin").value(2008))
                .andExpect(jsonPath("$.min[0].followingWin").value(2009))
                .andExpect(jsonPath("$.min[1].producer").value("Producer 2"))
                .andExpect(jsonPath("$.min[1].interval").value(1))
                .andExpect(jsonPath("$.min[1].previousWin").value(2018))
                .andExpect(jsonPath("$.min[1].followingWin").value(2019))
                .andExpect(jsonPath("$.max", hasSize(2)))
                .andExpect(jsonPath("$.max[0].producer").value("Producer 1"))
                .andExpect(jsonPath("$.max[0].interval").value(99))
                .andExpect(jsonPath("$.max[0].previousWin").value(1900))
                .andExpect(jsonPath("$.max[0].followingWin").value(1999))
                .andExpect(jsonPath("$.max[1].producer").value("Producer 2"))
                .andExpect(jsonPath("$.max[1].interval").value(99))
                .andExpect(jsonPath("$.max[1].previousWin").value(2000))
                .andExpect(jsonPath("$.max[1].followingWin").value(2099));
    }
}
