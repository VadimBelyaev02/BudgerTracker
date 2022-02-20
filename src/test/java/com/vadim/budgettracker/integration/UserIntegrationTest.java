//package com.vadim.budgettracker.integration;
//
//import com.vadim.budgettracker.TestConfig;
//import com.vadim.budgettracker.config.ApplicationInit;
//import com.vadim.budgettracker.dto.UserDTO;
//import com.vadim.budgettracker.entity.enums.Role;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.servlet.ServletContext;
//
//import java.time.LocalDate;
//
//import static com.vadim.budgettracker.Mapper.toJson;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestConfig.class, ApplicationInit.class})
//@WebAppConfiguration("src/main/java/com/vadim/budgettracker")
//public class UserIntegrationTest {
//
//    private final String url = "http://localhost:8080/spring-mvc-test";
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setup() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
//    }
//
//    @Test
//    public void name() {
//        ServletContext servletContext = context.getServletContext();
//
//        assertNotNull(servletContext);
//        assertTrue(servletContext instanceof MockServletContext);
//        assertNotNull(context.getBean("userController"));
//    }
//
//    @Test
//    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
//
//        UserDTO userDTO = new UserDTO();
//        Long id = 32432L;
//        userDTO.setId(id);
//        userDTO.setEmail("dfgzxcvzxbbcvb@gmail.com");
//        userDTO.setNickname("sdfwwwws");
//        userDTO.setConfirmed(true);
//        userDTO.setRole(Role.ADMIN);
//        userDTO.setCreatedDate(LocalDate.now());
//        userDTO.setCurrency("sddfg");
//        userDTO.setLanguage("eng");
//        userDTO.setPassword("eroitueroitum");
//        userDTO.setMode("light");
//
//        System.out.println(toJson(userDTO));
//
//        this.mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(userDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"));
//
//
//        this.mvc.perform(get("/api/users"))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void dfg() throws Exception {
//
//        this.mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_XML))
//                .andDo(print())
//                .andExpect(status().isUnsupportedMediaType());
//    }
//
//    @Test
//    public void Given_UserDTO_When_TryingToSave_Then_ReturnCreatedUser() throws Exception {
//        UserDTO userDTO = new UserDTO();
//        Long id = 1L;
//
//        userDTO.setEmail("dfg@gmail.com");
//        userDTO.setNickname("sdfs");
//
//        this.mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
//                        .content(userDTO.toString().getBytes()))
//                .andDo(print())
//                .andExpect(status().isUnsupportedMediaType())
//                .andExpect(content().contentType("application/json;charset=UTF-8"));
//    }
//
//}
