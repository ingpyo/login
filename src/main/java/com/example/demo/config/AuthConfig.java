package com.example.demo.config;

import com.example.demo.domain.oauth.Oauth2Client;
import com.example.demo.domain.oauth.Oauth2Clients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class AuthConfig {

    @Bean
    public Oauth2Clients oAuth2Clients(Set<Oauth2Client> clients) {
        return new Oauth2Clients(clients);
    }
}
