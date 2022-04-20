package com.monopoly.config;

import org.springframework.beans.factory.annotation.Value;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EmailConfigurer {
    /**
     * Email host information
     */
    @Value("${spring.mail.host}")
    private String host;
    /**
     * Email port information
     */
    @Value("${spring.mail.port}")
    private int port;
    /**
     * Email username information
     */
    @Value("${spring.mail.username}")
    private String username;
    /**
     * Email password information
     */
    @Value("${spring.mail.password}")
    private String password;

}
