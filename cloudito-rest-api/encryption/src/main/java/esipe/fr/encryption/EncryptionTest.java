package esipe.fr.encryption;

public class EncryptionTest {

    public static void main(String[] args){


        FrequencyEncryption fe = new FrequencyEncryption(FrequencyEncryption.Langage.Francais);
        fe.decrypt("te st ");

    }
}
