package com.sigmatechnology.csa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lucianahaugen on 01/09/17.
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.sigmatechnology.csa"})
@PropertySource({"classpath:persistence.properties"})
public class PersistenceConfiguration {

    private final static Logger LOG = LoggerFactory.getLogger(PersistenceConfiguration.class);

    @Autowired
    private Environment environment;

    /** List of known properties available for modification)*/
    private String[] knownProperties = {
            "eclipselink.weaving",
            "eclipselink.ddl-generation",
            "eclipselink.ddl-generation.output-mode",
            "eclipselink.ddl.table-creation-suffix",
            "eclipselink.deploy-on-startup",
            "eclipselink.jdbc.cache-statements",
            "eclipselink.jdbc.batch-writing",
            "eclipselink.jdbc.batch-writing.size",
            "eclipselink.logging.level",
            "eclipselink.persistence-context.close-on-commit",
            "eclipselink.persistence-context.flush-mode",
            "eclipselink.persistence-context.persist-on-commit",
            "eclipselink.database.delimiters",
            "eclipselink.connection-pool.default.initial",
            "eclipselink.connection-pool.default.min",
            "eclipselink.connection-pool.default.max",
            "eclipselink.connection-pool.default.wait",
            "eclipselink.connection-pool.default.shared"
    };

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String driver = environment.getProperty("javax.persistence.jdbc.driver");
        String url = environment.getProperty("javax.persistence.jdbc.url");

        if (LOG.isDebugEnabled()) {
            Properties dataSourceProperties = new Properties();
            dataSourceProperties.put("javax.persistence.jdbc.driver", driver);
            dataSourceProperties.put("javax.persistence.jdbc.url", url);
            printProperties("DataSource", dataSourceProperties);
            dataSourceProperties.clear();
        }

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(environment.getProperty("javax.persistence.jdbc.user"));
        dataSource.setPassword(environment.getProperty("javax.persistence.jdbc.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());

        //scan entities + the new Spring converters
        entityManagerFactoryBean.setPackagesToScan("com.sigmatechnology.csa.model.entity", "org.springframework.data.jpa.convert.threeten");
        entityManagerFactoryBean.setPersistenceUnitName("com.sigmatechnology.csa.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(additionalProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    // prepare additional properties
    private Properties additionalProperties() {
        Properties defaultProperties = defaultProperties();
        if(LOG.isDebugEnabled()){
            printProperties("EclipseLink (default)", defaultProperties);
        }
        // Overrides
        return overrideProperties(defaultProperties);
    }

    /**
     *
     * @param defaultProperties - the default property settings
     * @return a merged propery set
     */
    private Properties overrideProperties(Properties defaultProperties) {
        Properties overrideProperties = new Properties();
        for(String property : knownProperties){
            String value = environment.getProperty(property);
            if(value != null && !value.equals(defaultProperties.getProperty(property))){
                defaultProperties.setProperty(property, value);
                if(LOG.isDebugEnabled()){
                    overrideProperties.put(property, value);
                }
            }
        }
        if(LOG.isDebugEnabled() && !overrideProperties.isEmpty()){
            printProperties("EclipseLink (override)", overrideProperties);
            overrideProperties.clear();
        }
        return defaultProperties;
    }

    // set default values
    private Properties defaultProperties() {
        Properties defaultProperties = new Properties();

        // TODO: add default properties

        return defaultProperties;
    }

    // debug print method
    private void printProperties(String title, Properties properties) {
        StringBuilder printBuilder = new StringBuilder(prettyPrint(title));
        for(Map.Entry<Object, Object> entry : properties.entrySet()){
            printBuilder.append(String.format("%s = %s%n", entry.getKey(), entry.getValue()));
        }
    }

    // add padding (=)
    private String prettyPrint(String... title) {
        StringBuilder builder = new StringBuilder();
        int offset = 0;
        if(title.length != 0){
            builder.append("\n\n=== ").append(title[0]).append(' ');
            offset = (5 + title[0].length());
        }
        for(int padding = 80 - offset; --padding >= 0;){
            builder.append('=');
        }
        return builder.append('\n').toString();
    }


}
