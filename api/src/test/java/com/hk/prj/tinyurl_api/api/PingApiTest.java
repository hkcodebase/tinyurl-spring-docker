package com.hk.prj.tinyurl_api.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PingApiTest {

    private static final String pingPath = "/ping";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void pingApiTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(pingPath))
                .andExpect(status().isOk());
    }
}
