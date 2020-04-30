package esipe.fr.Cloudito_encryption.model;

import esipe.fr.Cloudito_encryption.EncryptionClass;
import esipe.fr.Cloudito_encryption.service.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.lang.IllegalStateException;

@Component
public class AttributeEncryptor implements AttributeConverter<String, String> {

    private static final String AES = "AES";
    private final String SECRET;

    @Autowired
    private Environment env;

    private EncryptionSupport ec;

    public AttributeEncryptor(Environment env) throws Exception {
        //ici récuperation de la clé
        this.SECRET = Cloudito_encryption.getInstance(env).getAESKey();
        Cloudito_encryption.getInstance(env).testSpringIntegration();
        this.ec = new EncryptionSupport();
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return ec.encrypt(attribute,SECRET);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return ec.decrypt(dbData,SECRET);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}