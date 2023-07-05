package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AppControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("GET /public - without user authentication")
    public void getPublicContent() throws Exception {
        mvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Public content")));
    }

    @Test
    @WithMockUser
    @DisplayName("GET /private - with user authentication")
    public void getPrivateContent() throws Exception {
        mvc.perform(get("/private"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Private content")));
    }

    @Test
    @DisplayName("GET /private - without user authentication")
    public void getPrivateContentWithForbiddenRequest() throws Exception {
        mvc.perform(get("/private"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /admin/action1 - without user authentication")
    public void getAdminAction1ContentBeforeConfigured() throws Exception {
        mvc.perform(get("/admin/action1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("GET /admin/action1 - with user authentication - with the expected role")
    public void getAdminAction1ContentWithAuthenticatedUserAndWithExpectedRole() throws Exception {
        mvc.perform(get("/admin/action1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Admin Action 1 content")));
    }

    @Test
    @WithMockUser()
    @DisplayName("GET /admin/action1 - with user authentication - without the expected role")
    public void getAdminAction1ContentWithAuthenticatedUserAndWithoutExpectedRole() throws Exception {
        mvc.perform(get("/admin/action1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("GET /private - with user authentication")
    public void getPrivateContentWithValidUserAuthentication() throws Exception {
        mvc.perform(get("/private").with(httpBasic("appUser1", "appUserPwd")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Private content")));
    }

    @Test
    @DisplayName("GET /private - with failed user authentication")
    public void getPrivateContentWithFailedUserAuthentication() throws Exception {
        mvc.perform(get("/private").with(httpBasic("appUser1", "wrongPassword")))
                .andExpect(status().isUnauthorized());
    }
}