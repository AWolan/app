package com.amw.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.amw.app")
@PropertySource("classpath:/com/myco/app.properties")
@EnableJpaRepositories
public class AppConfig {

    @Autowired
    private Environment env;

}
