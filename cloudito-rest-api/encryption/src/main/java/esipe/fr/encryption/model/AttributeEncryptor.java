package esipe.fr.encryption.model;

import esipe.fr.encryption.EncryptionClass;
import esipe.fr.encryption.service.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.InvalidKeyException;
import java.lang.IllegalStateException;
import java.security.Key;
import java.util.Base64;

@Component
public class AttributeEncryptor implements AttributeConverter<String, String> {

    private static final String AES = "AES";
    private final String SECRET;

    @Autowired
    private Environment env;

    private EncryptionClass ec;

    public AttributeEncryptor(Environment env) throws Exception {
        //ici récuperation de la clé
        this.SECRET = Encryption.getInstance(env).getAESKey();
        Encryption.getInstance(env).testSpringIntegration();
        this.ec = new EncryptionClass();
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