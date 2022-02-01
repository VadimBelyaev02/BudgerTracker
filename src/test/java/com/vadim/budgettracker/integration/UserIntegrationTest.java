package com.vadim.budgettracker.integration;

import com.vadim.budgettracker.config.ApplicationConfig;
import com.vadim.budgettracker.config.ApplicationInit;
import com.vadim.budgettracker.config.H2DatabaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {H2DatabaseConfig.class, ApplicationInit.class}) // may be more
@WebAppConfiguration
public class UserIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }


}
