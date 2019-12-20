package esipe.fr.scope3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;

@Configuration
@EnableSwagger2
public class SwaggerConfigurationParcours {
    @Bean
    public Docket api3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Parcours")
                .select()
                .apis(RequestHandlerSelectors.basePackage("esipe.fr.scope3.controllers"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Parcours","All for sending notifications to customers"));
    }
}