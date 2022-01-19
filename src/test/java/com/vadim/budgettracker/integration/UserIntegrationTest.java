package com.vadim.budgettracker.integration;

import com.vadim.budgettracker.config.ApplicationConfig;
import com.vadim.budgettracker.config.ApplicationInit;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class, ApplicationInit.class}) // may be more
@WebAppConfiguration
public class UserIntegrationTest {


}
