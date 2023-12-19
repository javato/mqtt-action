package com.jroldan.mqttaction.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("actions")
public class ActionsProperties {
    private final Map<String, String> commands;
}
