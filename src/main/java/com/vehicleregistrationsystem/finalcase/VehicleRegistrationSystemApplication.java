package com.vehicleregistrationsystem.finalcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class VehicleRegistrationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleRegistrationSystemApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasenames(
                "classpath:/validationMessages_en"
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
