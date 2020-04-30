package esipe.fr.Cloudito_geolocation.services;


import esipe.fr.Cloudito_geolocation.exceptions.ApiException;
import esipe.fr.Cloudito_model.Customer;
import esipe.fr.Cloudito_model.CustomerLocation;
import esipe.fr.Cloudito_repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import esipe.fr.Cloudito_repositories.CustomerLocationRepository;

import java.util.Date;
import java.util.Optional;


@Service
public class GeolocationService {

    @Autowired
    CustomerLocationRepository customerLocationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    Environment env;

    private Logger logger = LogManager.getLogger("GeolocationService");

    public CustomerLocation getCustomerLocation(Long customerId) throws ApiException {

        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            CustomerLocation customerLocation = customerLocationRepository.findFirstByCustomerIdOrderByLastUpdateDesc(customerId);
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
        Optional<Customer> customer = customerRepository.findById(customerId);
        customerLocation.setLastUpdate(new Date());
        if(customer.isPresent()){
            if(customerLocation.getLocation() != null){
                customerLocation.setCustomer(customer.get());
                customerLocationRepository.save(customerLocation);
                logger.info("CREATE customer location saved : "+ customerLocation.getId());
                return customerLocation;
            }else{
                logger.warn("Customer location not saved : "+ customerLocation.getId());
                throw new ApiException(400,"content error");
            }
        }else{
            logger.warn("Customer not found");
            throw new ApiException(404,"No Customer found");
        }
    }
}
