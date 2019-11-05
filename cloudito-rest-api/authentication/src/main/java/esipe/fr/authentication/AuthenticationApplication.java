package esipe.fr.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(esipe.fr.authentication.AuthenticationApplication.class, args);
    }

}