package com.vadim.budgettracker.config;

import com.vadim.budgettracker.entity.enums.Permission;
import com.vadim.budgettracker.security.jwt.JwtConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@EnableWebMvc
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final JwtConfigurer jwtConfigurer;

    @Autowired
    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.cors().and()
                //.cors().disable()
                //  .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority(Permission.READ.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority(Permission.READ.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority(Permission.READ.getPermission())

                .antMatchers(HttpMethod.POST, "/api/operations/**").hasAuthority(Permission.READ.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/operations/**").hasAuthority(Permission.UPDATE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/operations").hasAuthority(Permission.DELETE.getPermission())

                .antMatchers(HttpMethod.POST, "/api/forgot_password/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/reset_password").authenticated()
                .antMatchers(HttpMethod.POST, "/api/register").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/register/confirm").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/change_password").authenticated()

                .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/users").hasAuthority(Permission.READ.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/users").hasAuthority(Permission.READ.getPermission())

//                .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
//
                .antMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("Access-Control-Allow-Origin", "Content-Type")
                .exposedHeaders("Access-Control-Allow-Origin", "Content-Type")
                .allowedOrigins("*")
        ;
    }
}