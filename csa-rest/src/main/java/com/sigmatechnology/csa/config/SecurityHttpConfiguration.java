package com.sigmatechnology.csa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Created by lucianahaugen on 01/09/17.
 */
@Configuration
@ConditionalOnProperty(name = "react.security.enabled")
@EnableWebSecurity
public class SecurityHttpConfiguration extends WebSecurityConfigurerAdapter{

    private static final Logger LOG = LoggerFactory.getLogger(SecurityHttpConfiguration.class);

    protected void configure(HttpSecurity http) throws Exception{
        LOG.debug("Disabling CSRF");
        http.csrf().disable();
    }

}
