package com.starwars.starwars_app;

import com.starwars.app.StarwarsAppApplication;
import com.starwars.app.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StarwarsAppApplication.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .param("username", "franco_test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void testAccessProtectedEndpointWithValidToken() throws Exception {
        String token = jwtUtil.generateToken("franco_test");

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/all")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
