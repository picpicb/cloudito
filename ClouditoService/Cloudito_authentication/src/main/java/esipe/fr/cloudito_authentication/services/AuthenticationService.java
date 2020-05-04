package esipe.fr.cloudito_authentication.services;

//import esipe.fr.repositories.CustomerRepository;
import esipe.fr.cloudito_authentication.entities.TOTP;
import esipe.fr.cloudito_authentication.exceptions.AuthenticationException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import esipe.fr.cloudito_model.Customer;
import esipe.fr.cloudito_repositories.CustomerRepository;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthenticationService {

    @Autowired
    CustomerRepository customerRepository;
    private Logger logger = LogManager.getLogger("AuthenticationService");

    public Customer getCustomer(Long customerId) throws AuthenticationException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            return customer.get();
        }else{
            logger.warn("Customer not found");
            throw new AuthenticationException(404,"No Customer found");
        }
    }

    public Customer getCustomer(String login) throws AuthenticationException{
        Optional<Customer> customer = customerRepository.findCustomerByLogin(login);
        if(customer.isPresent()){
            return customer.get();
        }else{
            logger.warn("Customer not found");
            throw new AuthenticationException(404,"No Customer found");
        }
    }

    public boolean verifyLoginPassword(String login, String pwd,Long customerId) throws AuthenticationException{
        Customer customer = this.getCustomer(customerId);
        if(customer.getLogin().equalsIgnoreCase(login) && customer.getPwd().equalsIgnoreCase(pwd)){
            return true;
        }else{
            return false;
        }
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public String newCustomer(Customer customer){
        try{
            this.getCustomer(customer.getLogin());
            return "Already Exist";
        }catch (AuthenticationException e){
            // Customer don't exist yet
            this.save(customer);
            return "Created";
        }
    }

    public boolean verifyCode(String code,Long customerId) throws AuthenticationException {
        Customer customer = this.getCustomer(customerId);
        if(customer.getsKey() == null || customer.getsKey().trim()==""){
            throw new AuthenticationException(400,"Error, please regenerate a key");
        }
        if(code.trim().length() != 6){
            return false;
        }
        if(code.equalsIgnoreCase(getTOTPCode(customer.getsKey()))){
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyUUID(UUID uuid, Long customerId) throws AuthenticationException{
        Customer customer = this.getCustomer(customerId);
        if(customer.getUuid() == null || customer.getUuid().toString().trim()==""){
            throw new AuthenticationException(400,"Error, WRONG TOKEN");
        }
        if(uuid.toString().equalsIgnoreCase(customer.getUuid().toString())){
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyTime(Date time, Long customerId) throws AuthenticationException{
        Customer customer = this.getCustomer(customerId);
        if(customer.getTime() == null || customer.getTime().toString().trim()==""){
            throw new AuthenticationException(400,"Error, WRONG DATE");
        }
        if((time.getTime()-customer.getTime().getTime()) < 300000){
            return true;
        }else{
            return false;
        }
    }

    public String getRandomSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        String secretKey = base32.encodeToString(bytes);
        // make the secret key more human-readable by lower-casing and
        // inserting spaces between each group of 4 characters
        return secretKey;//.toLowerCase().replaceAll("(.{4})(?=.{4})", "$1 ");
    }

    public String getTOTPCode(String secretKey) {
        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(normalizedBase32Key);
        String hexKey = Hex.encodeHexString(bytes);
        long time = (System.currentTimeMillis()*(2*3600*1000) / 1000) / 30;
        String hexTime = Long.toHexString(time);
        return TOTP.generateTOTP(hexKey, hexTime, "6");
    }
}
