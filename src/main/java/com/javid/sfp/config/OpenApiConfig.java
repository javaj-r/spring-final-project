package com.javid.sfp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author javid
 * Created on 5/31/2022
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Application API")
                        .description("Application[Maktab-69 final project] management API")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Javid-r")
                                .email("javid.rafiee@gmail.com")
                                .url(""))
                        .license(new License()
                                .name("Apache License Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }

}
