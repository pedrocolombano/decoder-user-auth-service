package com.ead.userauth.publisher;

import com.ead.commonlib.enumerated.ActionType;
import com.ead.userauth.component.ApplicationPropertyComponent;
import com.ead.userauth.dto.rabbitmq.UserEventDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationPropertyComponent applicationPropertyComponent;

    public void publishUserEvent(final UserEventDTO userEventDTO, final ActionType actionType) {
        userEventDTO.setActionType(actionType);
        rabbitTemplate.convertAndSend(applicationPropertyComponent.getUserEventExchange(), StringUtils.EMPTY, userEventDTO);
    }
}
