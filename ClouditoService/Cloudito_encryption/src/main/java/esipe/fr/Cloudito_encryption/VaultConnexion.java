package esipe.fr.Cloudito_encryption;

import java.security.Key;

public class VaultConnexion {

    private static final String AES = "AES";
    private Key cleAES;
    private static final VaultConnexion instance = new VaultConnexion();
    private VaultConnexion() {
        //this.cleAES = new SecretKeySpec(this.operations.read("").toString().getBytes(), AES);
    }
    public static final VaultConnexion getInstance(){
        return instance;
    }
    public String getKey(){
        return this.cleAES.toString();
    }

}
