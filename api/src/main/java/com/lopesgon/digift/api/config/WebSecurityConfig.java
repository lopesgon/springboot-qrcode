package com.lopesgon.digift.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import com.lopesgon.digift.api.config.properties.DigiftProperties;
import com.lopesgon.digift.api.config.properties.DigiftProperties.DigiftSecurityProperties;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(DigiftProperties.class)
public class WebSecurityConfig implements WebSecurityCustomizer {

    private final DigiftSecurityProperties digiftSecurityProperties;

    @Autowired
    public WebSecurityConfig(DigiftProperties digiftProperties) {
        this.digiftSecurityProperties = digiftProperties.getSecurity();
    }

    @Override
    public void customize(WebSecurity web) {
        web.ignoring()
                .antMatchers(digiftSecurityProperties.getPublicUrls().toArray(new String[0]));
    }
}
