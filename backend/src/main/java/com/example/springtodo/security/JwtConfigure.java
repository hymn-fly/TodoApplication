package com.example.springtodo.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="jwt")
@Configuration
@Setter
@Getter
public class JwtConfigure {

    private String secretKey;

    private Long expirySeconds;

    private String issuer;

}
