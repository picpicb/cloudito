package esipe.fr.geolocation.services;


import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.model.Customer;
import esipe.fr.model.CustomerLocation;
import esipe.fr.repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import esipe.fr.repositories.CustomerLocationRepository;

import java.util.Optional;


@Service
public class GeolocationService {

    @Autowired
    CustomerLocationRepository customerLocationRepository;
    @Autowired
    CustomerRepository customerRepository;

    private Logger logger = LogManager.getLogger("GeolocationService");

    public CustomerLocation getCustomerLocation(Long customerId) throws ApiException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            CustomerLocation customerLocation = customerLocationRepository.findCustomerLocationByCustomerId(customerId);
            if(customerLocation != null){
                logger.info("READ customer location: " + customerLocation.getId());
                return customerLocation;
            }else{
                throw new ApiException(404,"No Location found");
            }
        }else{
            logger.warn("Customer not found");
            throw new ApiException(404,"No Customer found");
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
