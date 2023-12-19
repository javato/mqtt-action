package com.jroldan.mqttaction.controller;

import com.jroldan.mqttaction.client.MqttSubscriber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MqttControllerImpl implements MessageController {

    private final MqttSubscriber mqttSubscriber;

    @PostConstruct
    @Override
    public void listen() {
        mqttSubscriber.connectAndSubscribe();
    }
}
