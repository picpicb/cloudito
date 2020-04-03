package esipe.fr.encryption;

public class EncryptionTest {

    public static void main(String[] args){


        EncryptionClass AES = new EncryptionClass();

        String secretKey = "test";
        String originalString = "stringenclair";

        AES.secretKey=secretKey;

        String encryptedString = AES.encrypt(originalString, secretKey) ;
        String decryptedString = AES.decrypt(encryptedString, secretKey) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);

    }
}
