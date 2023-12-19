package com.jroldan.mqttaction.usecase;

import com.jroldan.mqttaction.config.props.ActionsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExecuteActionUseCaseImpl implements ExecuteActionUseCase {

    private final ActionsProperties actionsProperties;

    @Override
    public void executeAction(String action) {
        Optional<String> command = Optional.ofNullable(actionsProperties.getCommands().get(action));

        if (command.isEmpty()) {
            log.warn("There is no action for {} payload", action);
        } else {
            try {
                Runtime.getRuntime().exec(command.get());
            } catch (IOException e) {
                log.error("Could not execute action '{}' / command '{}'", action, command);
                throw new RuntimeException(e);
            }
        }

    }
}
