package com.sigmatechnology.csa.config;

import com.sigmatechnology.csa.service.security.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lucianahaugen on 01/09/17.
 * CRSF : Cross-Site Request Forgery / One click attack / Session ridnig
 * JWT : Java web toolkit / programming model is component-based and event-driven
 */

@Configuration
@ConditionalOnProperty(name = "react.security.enabled", havingValue = "true")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityJwtConfiguration extends WebSecurityConfigurerAdapter{

    private static final Logger LOG = LoggerFactory.getLogger(SecurityJwtConfiguration.class);


    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        LOG.debug("Disabling CRSF - Cross-Site Request Forgery");
        http.csrf().disable();

        LOG.debug("Adding JWT filter to chain for /rest/**");
        http.antMatcher("/rest/**")
            .addFilterAfter(SecurityJwtFilter.createSecurityJwtFilter(authenticationManager(), authenticationFailedEntryPoint()), BasicAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOG.debug("Adding JWT security provider");
        auth.authenticationProvider(new SecurityJwtProvider(authenticationService));
    }

    @Bean
    public AuthenticationEntryPoint authenticationFailedEntryPoint() {
        return (request, response, e) -> {
            LOG.error("Authentication failed", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unable to authorize");
        };
    }

}
