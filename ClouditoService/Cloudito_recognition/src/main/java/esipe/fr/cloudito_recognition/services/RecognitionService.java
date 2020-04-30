package esipe.fr.cloudito_recognition.services;

import esipe.fr.cloudito_model.Customer;
import esipe.fr.cloudito_model.CustomerDetection;
import esipe.fr.cloudito_recognition.exceptions.ApiException;
import esipe.fr.cloudito_repositories.CustomerDetectionRepository;
import esipe.fr.cloudito_repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

@Service
public class RecognitionService {

    private Logger logger = LogManager.getLogger("RecognitionService");

    @Autowired
    CustomerDetectionRepository customerDetectionRepository;
    @Autowired
    CustomerRepository customerRepository;

    /**
     * Get last detection of the customer
     * @param customerID Identifier of the customer
     */
    public CustomerDetection getLastCustomerDetection(Long customerID) throws ApiException {
        Optional<Customer> customer = customerRepository.findById(customerID);
        if(customer.isPresent()){
            CustomerDetection customerDetection = customerDetectionRepository.findFirstByCustomerIdOrderByLastUpdateDesc(customerID);
            if (customerDetection.equals(null)){
                logger.info("No detection found for the customer id ("+customerID.toString()+")");
                throw new ApiException(404,"No detection found for the customer id ("+customerID.toString()+")");
            }else{
                logger.info("Reading results for customer detection: " + customerDetection.getId());
                return customerDetection;
            }
        }else{
            logger.warn("No customer found with that id ("+customerID.toString()+") was founded");
            throw new ApiException(404,"No customer found with that id ("+customerID.toString()+") was founded");
        }
    }

    public void startRecognition(byte[] face){
        logger.info("New face detected");
        logger.info("Start recognition");
        String personName = "";
        Socket soc = openConnection();
        try{
            //open streams
           // Socket soc = new Socket("localhost",2021);
            OutputStream out = soc.getOutputStream();
            InputStream in = soc.getInputStream();

            //building writer and reader
            PrintWriter writer = new PrintWriter(out, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            writer.println("HELLO");

            String line;
            while ((line = reader.readLine()) != null) {
                logger.info("MSG : "+line);
                switch (line){
                    case "HELLOACK" :
                        out.write(face);
                        break;
                    case "FACEACK" :
                        personName = reader.readLine();
                        logger.info("Personne recognized : "+personName);
                        writer.println("BYE");
                        break;
                    default :
                        break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Open a new connection
     * @return socket created
     */
    public Socket openConnection(){
        try{
            Socket soc = new Socket("localhost",2021);
            logger.info(soc.getLocalAddress().toString());
            return soc;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Socket();
    }


}
