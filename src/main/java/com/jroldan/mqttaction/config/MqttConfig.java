package com.jroldan.mqttaction.config;

import com.jroldan.mqttaction.config.props.GatewayProperties;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MqttConfig {
    private final GatewayProperties gatewayProperties;

    @Bean
    public MqttClient mqttClient() {
        try {
            return new MqttClient(gatewayProperties.getUri(), gatewayProperties.getClientId());
        } catch (MqttException e) {
            throw new RuntimeException("Could not create MQTT client");
        }
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(gatewayProperties.getUsername());
        options.setPassword(gatewayProperties.getPassword().toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        return options;
    }
}
