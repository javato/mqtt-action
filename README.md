# MQTT Action Executor

This Spring Boot project is designed to listen to a specific MQTT topic, receive messages, and execute predefined windows console commands based on the payload of the messages. It uses the Eclipse Paho MQTT library for MQTT communication.

## Table of Contents
- [Configuration](#configuration)

## Configuration

Configuration properties are specified in the `application.yaml` file, including MQTT broker details, topic, and action command mappings. Modify these properties according to your MQTT broker and application requirements.

```yaml
# application.yaml

gateway:
  uri: tcp://mqtt-broker-url:1883
  client-id: your-client-id
  username: your-username
  password: your-password
  topic: your-mqtt-topic

actions:
  commands:
    calc: "calc"
    sleep: "rundll32.exe powrprof.dll, SetSuspendState Sleep"
    example-action-3: "your-command-for-example-action-3"
# Add more action mappings as needed
