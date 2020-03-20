package esipe.fr.authentication.services;

import esipe.fr.repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class AuthenticationService {

    @Autowired
    CustomerRepository customerRepository;
    private Logger logger = LogManager.getLogger("AuthenticationService");
}
