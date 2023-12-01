package com.smartjob.crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
public class RegexProperties {


    @Value("${custom.passwordRegex}")
    private String passwordRegex;

    @Value("${custom.emailRegex}")
    private String emailRegex;

}
