package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AppControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getPublicContent() throws Exception {
        mvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Public content")));
    }

    @Test
    @WithMockUser
    public void getPrivateContent() throws Exception {
        mvc.perform(get("/private"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Private content")));
    }

    @Test
    public void getPrivateContentWithForbiddenRequest() throws Exception {
        mvc.perform(get("/private"))
                .andExpect(status().isForbidden());
    }
}