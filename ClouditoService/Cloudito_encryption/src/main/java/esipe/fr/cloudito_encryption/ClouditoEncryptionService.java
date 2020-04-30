package esipe.fr.cloudito_encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClouditoEncryptionService {

    public static void main(String[] args) {

        SpringApplication.run(esipe.fr.cloudito_encryption.ClouditoEncryptionService.class, args);
        //test des differents methodes encrypt/decrypt
        //FrequencyEncryption fe = new FrequencyEncryption(FrequencyEncryption.Langage.Francais);
        //fe.decrypt("te st ");
    }

}
