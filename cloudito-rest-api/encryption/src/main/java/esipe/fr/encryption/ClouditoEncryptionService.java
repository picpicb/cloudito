package esipe.fr.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class ClouditoEncryptionService {

    public static void main(String[] args) {

        SpringApplication.run(esipe.fr.encryption.ClouditoEncryptionService.class, args);
        //test des differents methodes encrypt/decrypt
        //FrequencyEncryption fe = new FrequencyEncryption(FrequencyEncryption.Langage.Francais);
        //fe.decrypt("te st ");
    }

}
