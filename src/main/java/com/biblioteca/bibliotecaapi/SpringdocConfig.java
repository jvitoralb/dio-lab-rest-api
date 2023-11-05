package com.biblioteca.bibliotecaapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition
@Configuration
public class SpringdocConfig {
    @Bean
    public OpenAPI baseOpenAPI() {
        Info apiInfo = new Info()
            .title("Biblioteca API")
            .version("1.0.0")
            .description(
                "Essa API foi desenvolvida utilizando o Spring Framework e seguindo os conceitos REST, " +
                "para a conclusão do Bootcamp do Santander 2024 - Backend Java." + "<br />" +
                "Para mais informações acesse o " +
                "<a href=\"https://github.com/jvitoralb/dio-lab-rest-api#readme\" target=\"_blank\">repositório</a>."
            );

        return new OpenAPI().info(apiInfo);
    }
}
