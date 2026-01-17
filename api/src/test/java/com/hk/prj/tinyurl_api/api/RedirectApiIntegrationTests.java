package com.hk.prj.tinyurl_api.api;

import com.hk.prj.tinyurl_api.model.Url;
import com.hk.prj.tinyurl_api.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class RedirectApiIntegrationTests {

    private static final String redirectPath = "/redirect/abc123";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @Test
    public void whenRedirect_thenStatusIs404() throws Exception {
        when(urlService.getUrl("abc123")).thenReturn(new Url());
        this.mockMvc.perform(MockMvcRequestBuilders.get(redirectPath))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenRedirect_thenStatusIs200() throws Exception {
        String originalUrl = "https://hemantkumar.dev";
        when(urlService.getUrl("abc123")).thenReturn(new Url(originalUrl, "abc123", null));

        this.mockMvc.perform(MockMvcRequestBuilders.get(redirectPath))
                .andExpect(status().isFound())
                .andExpect(header().exists("Location"))
        .andExpect(header().string("Location", originalUrl));
    }

}
