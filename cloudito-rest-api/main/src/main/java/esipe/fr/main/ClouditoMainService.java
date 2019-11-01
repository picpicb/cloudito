package esipe.fr.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"esipe.fr"})
@EnableSwagger2
public class ClouditoMainService {

    public static void main(String[] args) {
        SpringApplication.run(ClouditoMainService.class, args);
    }

}
