package com.jroldan.mqttaction.client;

import com.jroldan.mqttaction.config.props.GatewayProperties;
import com.jroldan.mqttaction.usecase.ExecuteActionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MqttSubscriber implements MqttCallback {

    private final GatewayProperties gatewayProperties;
    private final MqttConnectOptions mqttConnectOptions;
    private final MqttClient mqttClient;
    private final ExecuteActionUseCase useCase;

    public void connectAndSubscribe() {
        try {
            mqttClient.connect(mqttConnectOptions);
            mqttClient.subscribe(gatewayProperties.getTopic());
            mqttClient.setCallback(this);
        } catch (MqttException e) {
            throw new RuntimeException("Could not connect and subscribe");
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.warn("Connection lost: {}", cause.getMessage());
        connectAndSubscribe();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String action = new String(message.getPayload());
        log.info("Received message on topic '{}': {}", topic, action);

        useCase.executeAction(action);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Nothing
    }
}
