import esipe.fr.cloudito_encryption.ClouditoEncryptionService;
import esipe.fr.cloudito_encryption.EncryptionSupport;

import static org.junit.Assert.*;

import esipe.fr.cloudito_encryption.model.AttributeEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.AttributeConverter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClouditoEncryptionService.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EncryptionSupportTest {


    @Autowired
    private AttributeEncryptor attributeEncryptor;
    private static String stringTest = "Bonjour";

    @Test
    public final void testVaultIntegration(){
        if(attributeEncryptor == null){
            assert (false);
        }else {
           String converted = attributeEncryptor.convertToDatabaseColumn(stringTest);
           assertNotEquals(stringTest,converted);
        }
    }

    @Test
    public final void testEncryption() {
        EncryptionSupport AES = new EncryptionSupport();

        String secretKey = "test";
        String clearString = "stringenclair";
        String expectedEncryptedString = "5GKqlX/fUrOjE6TGxIJn/Q==";

        String encryptedString = AES.encrypt(clearString, secretKey) ;

        assertEquals(expectedEncryptedString,encryptedString);
    }

    @Test
    public final void testDecryption() {
        EncryptionSupport AES = new EncryptionSupport();

        String secretKey = "test";
        String encryptedString = "5GKqlX/fUrOjE6TGxIJn/Q==";
        String expectedClearString = "stringenclair";

        String decryptedString = AES.decrypt(encryptedString, secretKey) ;

        assertEquals(decryptedString,expectedClearString);
    }

}
