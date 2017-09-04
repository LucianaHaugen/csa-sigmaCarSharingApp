package com.sigmatechnology.csa.service.security;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.sigmatechnology.csa.entity.account.UserAccount;
import com.sigmatechnology.csa.entity.account.UserRoles;
import com.sigmatechnology.csa.service.UserAccountService;
import com.sigmatechnology.csa.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * Created by lucianahaugen on 04/09/17.
 */
@Service
public class AuthenticationService {

    public static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);
    public static final Cache<String, String> cache;

    static{
        cache = CacheBuilder.newBuilder()
            .maximumSize(1000000)
            .expireAfterAccess(1, TimeUnit.DAYS)
            .build();
    }

    @Autowired
    private UserAccountService userAccountService;

    public List<String> verifyJwt(String token){

       return verifyJwt(JwtHelper.decode(token));
    }

    public List<String> verifyJwt(Jwt jwt){
        ClaimsDTO claims = getClaims(jwt);
        UserAccount user = getUser(claims.getSub());
        verifyJwt(jwt, user.getPassword());
        verifyJwtNotExpired(claims.getExp());
        verifyFirstTimeForThisRequest(claims.getJti());

        return getRoles(user);
    }

    private ClaimsDTO getClaims(Jwt jwt) {
        ClaimsDTO claims = Json.fromJson(jwt.getClaims(), ClaimsDTO.class);

        if(Objects.isNull(claims)) {
            LOG.error("Could not decode claims from JSON");
            throw new BadCredentialsException("Claims invalid.");
        }

        if(Objects.isNull(claims.getSub())) {
            LOG.error("Claims sub field is null, must be valid user name");
            throw new BadCredentialsException("Claims invalid.");
        }

        if(Objects.isNull(claims.getExp())) {
            LOG.error("Claims exp field is null, must be valid expire time");
            throw new BadCredentialsException("Claims invalid.");
        }

        if(Objects.isNull(claims.getJti())) {
            LOG.error("Claims JTI field is null, must be valid UUID");
            throw new BadCredentialsException("Claims invalid.");
        }

        return claims;
    }

    private UserAccount getUser(String userName){
        try {
            return userAccountService.fetchAccount(userName);
        }catch (Exception e) {
            LOG.error("Claims sub (username) invalid", e);
            throw new BadCredentialsException("Claims sub (username) invalid", e);
        }
    }

    private void verifyJwt(Jwt jwt, String password){
        try {
            jwt.verifySignature(new MacSigner(password));
        }catch (Exception e){
            LOG.error("Invalid signature", e);
            throw  new BadCredentialsException("Invalid signature", e);
        }
    }

    private void verifyJwtNotExpired(Long expired){
        if(System.currentTimeMillis() / 1000 > expired){
            LOG.error("JWT has expired");
            throw new BadCredentialsException("JWT has expired");
        }
    }

    private void verifyFirstTimeForThisRequest(String jti){
        if(Objects.nonNull(cache.getIfPresent(jti))){
            LOG.error("JTI already executed");
            throw new BadCredentialsException("JTI already executed");
        }
        cache.put(jti, jti);
    }

    private List<String> getRoles(UserAccount user){
        List<String> roles = Lists.newArrayList();

        if(Objects.nonNull(user.getRoles()) && !user.getRoles().isEmpty()){
            user
                .getRoles()
                .forEach((role) -> roles.add(role.getRole().name()));
        }else{
            roles.add(UserRoles.ReadOnly.name());
        }
        return roles;
    }

}
