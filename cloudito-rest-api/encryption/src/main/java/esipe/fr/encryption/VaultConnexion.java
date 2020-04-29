package esipe.fr.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
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
