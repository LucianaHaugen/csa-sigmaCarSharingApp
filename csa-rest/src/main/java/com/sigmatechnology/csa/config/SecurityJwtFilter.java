package com.sigmatechnology.csa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lucianahaugen on 04/09/17.
 */
public final class SecurityJwtFilter extends GenericFilterBean{

    private static final Logger LOG = LoggerFactory.getLogger(SecurityJwtFilter.class);

    private final AuthenticationManager authenticationManager;
    private final AuthenticationEntryPoint authenticationFailurenEntryPoint;

    // Constructor to initiate AuthenticationManager
    private SecurityJwtFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationFailurenEntryPoint){
        this.authenticationManager = authenticationManager;
        this.authenticationFailurenEntryPoint = authenticationFailurenEntryPoint;
    }

    public static SecurityJwtFilter createSecurityJwtFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationFailurenEntryPoint){
        checkNotNull(authenticationManager);
        checkNotNull(authenticationFailurenEntryPoint);

        return new SecurityJwtFilter(authenticationManager, authenticationFailurenEntryPoint);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try{
            Jwt jwt = jwt(httpRequest);

            if (Objects.isNull(jwt)) {
                throw new BadCredentialsException("Request must contain JWT token.");
            }

            Authentication authRequest = new PreAuthenticatedAuthenticationToken(jwt, null);
            Authentication authResult = authenticationManager.authenticate(authRequest);

            SecurityContextHolder.getContext().setAuthentication(authResult);
            chain.doFilter(request, response);

        }catch (AuthenticationException e){
            SecurityContextHolder.clearContext();
            authenticationFailurenEntryPoint.commence(httpRequest, (HttpServletResponse) response, e);
        }
    }

    private Jwt jwt(HttpServletRequest httpServletRequest) {
        String start = "Bearer ";
        Enumeration<String> headerValues = httpServletRequest.getHeaders(HttpHeaders.AUTHORIZATION);

        while(headerValues.hasMoreElements()){
            String headerValue = headerValues.nextElement();
            LOG.debug("Authorization: " + headerValue);

            if(headerValue != null && headerValue.startsWith(start)){
                try {
                    return JwtHelper.decode(headerValue.substring(start.length()));
                }catch (Exception e){
                    LOG.error("Could not decode JWT token.", e);
                }
            }
        }

        return  null;
    }



}
