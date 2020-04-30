package esipe.fr.encryption.service;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class Encryption {

    private static Encryption instance;
    private VaultConfig vaultConfig;
    //@Value("${app.vault.hostVault}")
    private String hostVault;
    //@Value("${app.vault.tokenVault}")
    private String tokenVault;
    private String AESKey;
    private Vault vault;

    public static Encryption getInstance(Environment env) {
        if(instance==null)
            instance = new Encryption(env);
        return instance;
    }

    private Encryption(Environment env){
        this.hostVault = env.getProperty("app.vault.hostVault");
        //app.vault.tokenVault
        this.tokenVault = env.getProperty("app.vault.tokenVault");
        //app.vault.hostVault
        try {
            this.vaultConfig = new VaultConfig()
                    .address(hostVault)
                    .token(tokenVault)
                    .build();
            this.vault = new Vault(this.vaultConfig);
            this.AESKey = this.vault.logical()
                    .read("secret/AES_Key_1")
                    .getData().get("value");
        }catch(VaultException e){
            e.printStackTrace();
        }
    }
    public VaultConfig getVaultConfig(){
        return this.vaultConfig;
    }
    public void testSpringIntegration(){
        try {
            final String value = this.vault.logical()
                    .read("secret/AES_Key_1")
                    .getData().get("value");
            System.out.println("test Encryption class " + this.hostVault + "; and " + this.tokenVault+" et le secret est :"+value);
        } catch (VaultException e) {
            e.printStackTrace();
        }
    }
    public String getAESKey(){
        return this.AESKey;
    }
}
