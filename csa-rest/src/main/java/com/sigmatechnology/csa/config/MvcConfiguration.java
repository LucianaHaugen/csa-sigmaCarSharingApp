package com.sigmatechnology.csa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucianahaugen on 01/09/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sigmatechnology.csa"})
@Import({SecurityHttpConfiguration.class, SecurityJwtConfiguration.class})
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        registry
            .addResourceHandler("/**")
            .addResourceLocations("/");
    }

    public void configuredMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(converter());
        converters.add(customPDFConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter converter(){
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        return jackson2HttpMessageConverter;
    }

    // required; otherwise we will get a HTTP 406 Error Message
    @Bean
    public HttpMessageConverter customPDFConverter(){
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

        List<MediaType> supportApplicationTypes = new ArrayList<MediaType>();
        MediaType pdfApplication = new MediaType("application", "pdf");
        supportApplicationTypes.add(pdfApplication);
        arrayHttpMessageConverter.setSupportedMediaTypes(supportApplicationTypes);

        return arrayHttpMessageConverter;
    }

}
