import esipe.fr.Cloudito_encryption.EncryptionClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptionTest {



    @Test
    public final void testEncryption() {
        EncryptionClass AES = new EncryptionClass();

        String secretKey = "test";
        String clearString = "stringenclair";
        String expectedEncryptedString = "5GKqlX/fUrOjE6TGxIJn/Q==";

        String encryptedString = AES.encrypt(clearString, secretKey) ;

        assertEquals(expectedEncryptedString,encryptedString);
    }

    @Test
    public final void testDecryption() {
        EncryptionClass AES = new EncryptionClass();

        String secretKey = "test";
        String encryptedString = "5GKqlX/fUrOjE6TGxIJn/Q==";
        String expectedClearString = "stringenclair";

        String decryptedString = AES.decrypt(encryptedString, secretKey) ;

        assertEquals(decryptedString,expectedClearString);
    }

}
