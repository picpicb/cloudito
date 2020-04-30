import esipe.fr.encryption.EncryptionSupport;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptionSupportTest {



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
