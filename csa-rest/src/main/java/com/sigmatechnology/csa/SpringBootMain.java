package com.sigmatechnology.csa;

import com.sigmatechnology.csa.config.MvcConfiguration;
import com.sigmatechnology.csa.config.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by lucianahaugen on 05/09/17.
 */
@SpringBootApplication
@Import({PersistenceConfiguration.class, MvcConfiguration.class})
public class SpringBootMain {

    public static void main(String[] args){
        SpringApplication.run(SpringBootMain.class, args);
    }
}
