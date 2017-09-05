package com.sigmatechnology.csa.config;

import com.sigmatechnology.csa.service.security.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lucianahaugen on 04/09/17.
 */
public class SecurityJwtProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityJwtProvider.class);

    private final AuthenticationService authenticationService;

    public SecurityJwtProvider(AuthenticationService authenticationService) {
        checkNotNull(authenticationService);
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Jwt jwt = (Jwt)authentication.getPrincipal();

        if(Objects.isNull(jwt)) {
            throw new BadCredentialsException("No JWT principal provided");
        }

        LOG.debug("Authenticating JWT claims " + jwt.getClaims());
        List<String> roles = authenticationService.verifyJwt(jwt);
        LOG.debug("Authenticated with roles " + roles);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if(roles != null) {
            roles.forEach((role) -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        }

        return new PreAuthenticatedAuthenticationToken(jwt, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
