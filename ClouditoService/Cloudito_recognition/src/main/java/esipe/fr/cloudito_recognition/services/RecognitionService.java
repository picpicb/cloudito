package esipe.fr.cloudito_recognition.services;

import esipe.fr.cloudito_model.Customer;
import esipe.fr.cloudito_model.CustomerDetection;
import esipe.fr.cloudito_model.DetectionList;
import esipe.fr.cloudito_recognition.exceptions.ApiException;
import esipe.fr.cloudito_repositories.CustomerDetectionRepository;
import esipe.fr.cloudito_repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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



    public CustomerDetection addRecognition(CustomerDetection recognition) throws ApiException {
        // Test if request is not empty
        if(recognition.getCustomerId() != null && recognition.getRecognitionDate() != null){
            // Test if the customer exist
            Optional<Customer> customer = customerRepository.findById(recognition.getCustomerId());
            if(customer.isPresent()) {
                customerDetectionRepository.save(recognition);
                logger.info("New customer detected : " + recognition.getId());
                return recognition;
            }else {
                logger.warn("Customer not found");
                throw new ApiException(404,"No Customer found");
            }
        }else{
            logger.warn("Customer detection not saved : "+recognition.getId());
            throw new ApiException(400,"content error");
        }
    }

    public DetectionList findAll(int page, int pageSize){
        DetectionList detectionList = new DetectionList();
        detectionList.setPage(page);
        detectionList.setNbElements(pageSize);
        Pageable pageable = PageRequest.of(page,pageSize);
        for (CustomerDetection d : customerDetectionRepository.findAll(pageable)) {
            detectionList.addDataItem(d);
        }
        return detectionList;
    }





}
