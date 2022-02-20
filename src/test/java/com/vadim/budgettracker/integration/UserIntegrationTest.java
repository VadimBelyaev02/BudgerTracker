package com.vadim.budgettracker.integration;

import com.vadim.budgettracker.TestConfig;
import com.vadim.budgettracker.config.ApplicationConfig;
import com.vadim.budgettracker.config.ApplicationInit;
import com.vadim.budgettracker.config.H2DatabaseConfig;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, ApplicationInit.class})
@WebAppConfiguration("src/main/java/com/vadim/budgettracker")
public class UserIntegrationTest {

    private final String url = "http://localhost:8080/spring-mvc-test";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void name() {
        ServletContext servletContext = context.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(context.getBean("userController"));
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
        this.mvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void dfg() throws Exception {
        this.mvc.perform(post("/api/users"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


//    @Test
//    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
////        this.mvc.perform(get("/api/users")).andDo(print())
////                .andExpect();
//        MvcResult mvcResult = this.mvc.perform(get(url + "/api/users/1"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.email").value("olivertears@gmail.com"))
//                .andReturn();
//
//        assertEquals("application/json;charset=UTF-8",
//                mvcResult.getResponse().getContentType());
//    }

}
