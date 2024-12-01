package com.baykov.daniel.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@PropertySource("classpath:common.lib.properties")
@ConfigurationProperties(prefix = "swagger")
@ConfigurationPropertiesScan
public class SwaggerConstantConfig {

    @NotEmpty
    private String docsPath;

    @NotEmpty
    private String uiPath;

    @NotEmpty
    private String resourcesPath;

    @NotEmpty
    private String indexCss;

    @NotEmpty
    private String uiCss;

    @NotEmpty
    private String uiCssMap;

    @NotEmpty
    private String initJs;

    @NotEmpty
    private String uiBundleJs;

    @NotEmpty
    private String uiBundleJsMap;

    @NotEmpty
    private String uiStandaloneJs;

    @NotEmpty
    private String uiStandaloneJsMap;

    @NotEmpty
    private String favicon16;

    @NotEmpty
    private String favicon32;

    public Set<String> getSwaggerUris() {
        Set<String> uris = new HashSet<>();
        uris.add(docsPath);
        uris.add(uiPath);
        uris.add(resourcesPath);
        uris.add(indexCss);
        uris.add(uiCss);
        uris.add(uiCssMap);
        uris.add(initJs);
        uris.add(uiBundleJs);
        uris.add(uiBundleJsMap);
        uris.add(uiStandaloneJs);
        uris.add(uiStandaloneJsMap);
        uris.add(favicon16);
        return uris;
    }

    public boolean isSwaggerEndpoint(String requestUri) {
        for (String swaggerUri : getSwaggerUris()) {
            if (requestUri.startsWith(swaggerUri)) {
                return true;
            }
        }

        return false;
    }
}

