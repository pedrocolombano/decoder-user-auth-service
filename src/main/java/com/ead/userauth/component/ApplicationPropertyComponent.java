package com.ead.userauth.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationPropertyComponent {

    @Value("${ead.broker.exchange.userEvent}")
    private String userEventExchange;

}
