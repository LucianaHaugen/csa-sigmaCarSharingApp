package com.sigmatechnology.csa.service.security;

/**
 * Created by lucianahaugen on 04/09/17.
 */
public class ClaimsDTO {

    private String sub;
    private String jti;
    private Long iat;
    private Long exp;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {

        this.sub = sub;
    }

    public String getJti() {

        return jti;
    }

    public void setJti(String jti) {

        this.jti = jti;
    }

    public Long getIat() {

        return iat;
    }

    public void setIat(Long iat) {

        this.iat = iat;
    }

    public Long getExp() {

        return exp;
    }

    public void setExp(Long exp) {

        this.exp = exp;
    }
}

