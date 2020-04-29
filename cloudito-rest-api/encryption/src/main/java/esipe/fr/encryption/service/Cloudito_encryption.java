package esipe.fr.encryption.service;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

public class Cloudito_encryption {

    private static Cloudito_encryption instance;
    private VaultConfig vaultConfig;
    //@Value("${app.vault.hostVault}")
    private String hostVault;
    //@Value("${app.vault.tokenVault}")
    private String tokenVault;
    private Vault vault;
    private String aesKey;

    public static Cloudito_encryption getInstance(Environment env) {
        if(instance==null)
            instance = new Cloudito_encryption(env);
        return instance;
    }
    private Cloudito_encryption(Environment env){
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
            this.aesKey = this.vault.logical()
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
            System.out.println("test Cloudito_encryption class " + this.hostVault + "; and " + this.tokenVault+" et le secret est :"+value);
        } catch (VaultException e) {
            e.printStackTrace();
        }
    }
    public String getAesKey(){
        return this.aesKey;
    }
}
