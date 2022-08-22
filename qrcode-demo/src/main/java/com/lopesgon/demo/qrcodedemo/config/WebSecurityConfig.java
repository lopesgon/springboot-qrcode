package com.lopesgon.demo.qrcodedemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import com.lopesgon.demo.qrcodedemo.config.properties.AppProperties;
import com.lopesgon.demo.qrcodedemo.config.properties.AppProperties.AppSecurityProperties;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(AppProperties.class)
public class WebSecurityConfig implements WebSecurityCustomizer {

    private final AppSecurityProperties appSecurityProperties;

    @Autowired
    public WebSecurityConfig(AppProperties appProperties) {
        this.appSecurityProperties = appProperties.getSecurity();
    }

    @Override
    public void customize(WebSecurity web) {
        web.ignoring()
                .antMatchers(appSecurityProperties.getPublicUrls().toArray(new String[0]));
    }
}
