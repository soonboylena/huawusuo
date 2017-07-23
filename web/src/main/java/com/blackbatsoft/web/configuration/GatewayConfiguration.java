package com.blackbatsoft.web.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by sunb on 2017/7/12.
 */
@Component
@ConfigurationProperties(prefix = "gateway")
public class GatewayConfiguration {

    private String scheme = "http";
    private String host = "127.0.0.1";
    private String port = "5000";

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
