package esipe.fr.recognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@AutoConfigurationPackage
@EnableJpaRepositories(basePackages = {"esipe.fr"})
@EntityScan(basePackages = {"esipe.fr"})
@ComponentScan(basePackages = {"esipe.fr"})

public class ClouditoRecognitionService {
    public static void main(String[] args) {
        SpringApplication.run(ClouditoRecognitionService.class, args);
    }
}
