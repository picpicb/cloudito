package esipe.fr.main.configuration;

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
public class SwaggerConfigurationMain {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("172.31.254.54"+":28080")
                .groupName("MainAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("esipe.fr.main.controllers"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("main","CRUD for Main"));
    }
}