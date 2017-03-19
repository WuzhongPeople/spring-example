package io.vilya.example.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Vilya
 * 2017/3/19 17:38
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        LOG.info("SpringSecurityConfig");
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}
