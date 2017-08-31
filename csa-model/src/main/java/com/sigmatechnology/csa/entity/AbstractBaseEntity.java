package com.sigmatechnology.csa.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
