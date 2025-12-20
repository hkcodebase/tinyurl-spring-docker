package com.hk.prj.tinyurl_api.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.prj.tinyurl_api.openapi.api.GenerateApiController;
import com.hk.prj.tinyurl_api.openapi.model.GenerateShortUrl200Response;
import com.hk.prj.tinyurl_api.openapi.model.GenerateShortUrlRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GenerateApiIntegrationTests {

    private static final String generatePath = "/generate";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGenerate_thenStatusIs200() throws Exception {
        GenerateShortUrlRequest generateShortUrlRequest = new GenerateShortUrlRequest();
        generateShortUrlRequest.setOriginalUrl(URI.create("https://somesampleurl.com"));

        GenerateShortUrl200Response generateShortUrl200Response = new GenerateShortUrl200Response();
        generateShortUrl200Response.setShortenedUrl(URI.create("http://localhost:8080/api/v1/abc123"));

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(MockMvcRequestBuilders.post(generatePath)
                        .content(objectMapper.writeValueAsString(generateShortUrlRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

}
