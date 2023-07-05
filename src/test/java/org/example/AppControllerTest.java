package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AppControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getPublicContent() throws Exception {
        // spring-security should block the access by default without any configuration
        mvc.perform(get("/public"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getPrivateContent() throws Exception {
        // spring-security should block the access by default without any configuration
        mvc.perform(get("/private"))
                .andExpect(status().isUnauthorized());
    }
}