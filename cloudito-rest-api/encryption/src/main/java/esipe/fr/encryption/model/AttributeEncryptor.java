package esipe.fr.encryption.model;

import esipe.fr.encryption.EncryptionClass;
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
    private static final String SECRET = "secret-key-12345";

    private EncryptionClass ec;

    public AttributeEncryptor() throws Exception {
        //ici récuperation de la clé
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