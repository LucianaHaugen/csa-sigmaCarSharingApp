package com.sigmatechnology.csa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * DemoApplication!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"csa"})
@EntityScan(basePackages = {"csa"})
@ComponentScan(basePackages = {"csa"})
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }
}
