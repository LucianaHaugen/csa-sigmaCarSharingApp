package com.sigmatechnology.csa.json;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by lucianahaugen on 05/09/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractJsonObject {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
