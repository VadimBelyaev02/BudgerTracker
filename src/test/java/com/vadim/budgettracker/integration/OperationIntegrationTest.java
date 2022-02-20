package com.vadim.budgettracker.integration;

import com.vadim.budgettracker.TestConfig;
import com.vadim.budgettracker.config.ApplicationInit;
import com.vadim.budgettracker.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, ApplicationInit.class})
@WebAppConfiguration(value = "src/main/java/com/vadim/budgettracker")
public class OperationIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void dfg() throws Exception {
        this.mvc.perform(post("/api/operations").contentType(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
//                .andExpect(jsonPath("$.status").value("Unsupported Media Type"))
        // .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
