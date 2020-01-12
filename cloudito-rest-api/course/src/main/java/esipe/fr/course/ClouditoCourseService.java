package esipe.fr.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(scanBasePackages = {"esipe.fr"})
@SpringBootApplication
@EnableSwagger2
public class ClouditoCourseService extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ClouditoCourseService.class, args);
    }

}
