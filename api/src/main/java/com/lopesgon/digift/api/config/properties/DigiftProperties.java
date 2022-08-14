package com.lopesgon.digift.api.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app")
public class DigiftProperties {

    private String test;
    private DigiftSecurityProperties security;
    private DigiftFeaturesProperties features;

    @Data
    public static class DigiftSecurityProperties {
        private List<String> publicUrls;
    }

    @Data
    public static class DigiftFeaturesProperties {

    }
}
