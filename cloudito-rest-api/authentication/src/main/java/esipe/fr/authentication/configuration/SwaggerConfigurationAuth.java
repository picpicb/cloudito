package esipe.fr.authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfigurationAuth {
    @Bean
    public Docket api2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Authentication")
                .select()
                .apis(RequestHandlerSelectors.basePackage("esipe.fr.authentication.controllers"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Auth","Authentication Resources"));
    }
}