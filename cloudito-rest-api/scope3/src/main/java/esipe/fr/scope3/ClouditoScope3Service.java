package esipe.fr.scope3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(scanBasePackages = {"esipe.fr"})
@SpringBootApplication
@EnableSwagger2
public class ClouditoScope3Service extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ClouditoScope3Service.class, args);
    }

}
