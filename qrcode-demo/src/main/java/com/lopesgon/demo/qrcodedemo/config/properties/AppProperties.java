package com.lopesgon.demo.qrcodedemo.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String test;
    private AppSecurityProperties security;
    private AppFeaturesProperties features;

    @Data
    public static class AppSecurityProperties {
        private List<String> publicUrls;
    }

    @Data
    public static class AppFeaturesProperties {

    }
}
