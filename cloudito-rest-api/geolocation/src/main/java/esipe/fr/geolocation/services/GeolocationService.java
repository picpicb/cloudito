package esipe.fr.geolocation.services;

import esipe.fr.geolocation.entities.CustomerLocation;
import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.geolocation.repositories.CustomerLocationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GeolocationService {
   @Autowired
   CustomerLocationRepository customerLocationRepository;

    private Logger logger = LogManager.getLogger("GeolocationService");

    public CustomerLocation getCustomerLocation(Long customerId) throws ApiException {
        Optional<CustomerLocation> customerLocation = customerLocationRepository.findById(customerId);
        if(customerLocation.isPresent()){
            logger.info("READ customer location: " + customerLocation.get().getId());
            return customerLocation.get();
        }else{
            logger.warn("Customer not found");
            throw new ApiException(404,"No location found");
        }
    }


    public CustomerLocation addCustomerLocation(Long customerId, CustomerLocation customerLocation) throws ApiException {

        if(customerLocation.getId() != null && customerLocation.getLastUpdate() != null && customerLocation.getLocation() != null){
            customerLocationRepository.save(customerLocation);
            logger.info("CREATE customer location saved : "+ customerLocation.getId());
            return getCustomerLocation(customerLocation.getId());
        }else{
            logger.warn("Customer location not saved : "+ customerLocation.getId());
            throw new ApiException(400,"content error");
        }
    }
}
