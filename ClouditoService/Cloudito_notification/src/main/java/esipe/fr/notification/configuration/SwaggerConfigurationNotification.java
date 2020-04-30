package esipe.fr.notification.configuration;

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
public class SwaggerConfigurationNotification {
    @Bean
    public Docket api3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Notification")
                .select()
                .apis(RequestHandlerSelectors.basePackage("esipe.fr.notification.controllers"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Notification","All for sending notifications to customers"));
    }
}