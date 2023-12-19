package com.jroldan.mqttaction.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("gateway")
public class GatewayProperties {
    private String uri;
    private String clientId;
    private String topic;
    private String username;
    private String password;
}
