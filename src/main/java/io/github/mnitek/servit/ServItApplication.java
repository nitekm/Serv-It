package io.github.mnitek.servit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ServItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServItApplication.class, args);
    }
}
