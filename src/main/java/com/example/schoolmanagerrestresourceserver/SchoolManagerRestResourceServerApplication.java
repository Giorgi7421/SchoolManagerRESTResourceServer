package com.example.schoolmanagerrestresourceserver;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolManagerRestResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagerRestResourceServerApplication.class, args);
    }

}
