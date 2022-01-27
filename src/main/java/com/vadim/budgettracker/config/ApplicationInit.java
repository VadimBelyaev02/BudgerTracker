package com.vadim.budgettracker.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import org.h2.server.web.WebServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext
//                .addServlet("H2Console", WebServlet.class)
//                .addMapping("/console/*");
//    }
}