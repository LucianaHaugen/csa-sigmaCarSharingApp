package com.sigmatechnology.csa.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by lucianahaugen on 01/09/17.
 */
public class MvcInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(PersistenceConfiguration.class);
        context.register(MvcConfiguration.class);
        context.setServletContext(container);

    }
}
