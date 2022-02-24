package com.vadim.budgettracker.integration;

import com.vadim.budgettracker.TestConfig;
import com.vadim.budgettracker.config.ApplicationInit;
import com.vadim.budgettracker.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Random;

import static com.vadim.budgettracker.EntitiesFactory.getUserDTO;
import static com.vadim.budgettracker.Mapper.toJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, ApplicationInit.class})
@WebAppConfiguration("src/main/java/com/vadim/budgettracker")
public class UserIntegrationTest {

    private final String ENDPOINT = "/api/users";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void givenServletContext_whenInitialize_thenContextExists() {
        ServletContext servletContext = context.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(context.getBean("userController"));
    }

    @Test
    public void init() {

    }

    @Test
    public void givenNothing_whenGetRequest_thenReturnJsonAnsOkStatus() throws Exception {
        mvc.perform(get(ENDPOINT))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUserDto_whenPostRequest_thenReturnSavedUser() throws Exception {
        UserDTO userDTO = getUserDTO();
        this.mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname").value(userDTO.getNickname()));

    }

    @Test
    public void givenUserId_whenGetRequest_thenReturnUserById() throws Exception {
        Long id = 1L;
        mvc.perform(get(ENDPOINT + "/{id}", id))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void givenUserDTO_whenTryingToUpdate_thenReturnNewUser() throws Exception {
        UserDTO userDTO = getUserDTO();
        Random random = new Random();
        String newNickname = String.valueOf(random.nextDouble());
        userDTO.setNickname(newNickname);
        this.mvc.perform(put(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nickname").value(newNickname))
                .andExpect(jsonPath("$.id").value(userDTO.getId()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }

    @Test
    public void givenUserId_whenTryingToDelete_returnNoContent() throws Exception {
        Long id = 1L;
        this.mvc.perform(delete(ENDPOINT + "/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }



}
