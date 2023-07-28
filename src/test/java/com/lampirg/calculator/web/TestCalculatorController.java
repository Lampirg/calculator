package com.lampirg.calculator.web;

import com.lampirg.calculator.logic.Calculator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorController.class)
@DisplayName("Test controller")
public class TestCalculatorController {

    @MockBean
    private Calculator<String, String> calculator;
    @Autowired
    private MockMvc mockMvc;

    private String body = "{\"expression\": \"%s\"}";

    @Test
    @SneakyThrows
    void testPost() {
        Mockito.when(calculator.calculate("14+5*8-15")).thenReturn("39");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/calc")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(String.format(body, "14 + 5*8 - 15"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("39"));
    }

    @Test
    @SneakyThrows
    void testGet() {
        Mockito.when(calculator.calculate("14+5*8-15")).thenReturn("39");
        mockMvc.perform(MockMvcRequestBuilders.get("/calc/14+5*8-15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("39"));
    }
}
