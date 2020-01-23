package esipe.fr.geolocation.services;

import esipe.fr.geolocation.entities.UserLocation;
import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.geolocation.repositories.AccessPointRepository;
import esipe.fr.geolocation.repositories.UserLocationRepository;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GeolocationService {
   @Autowired
    UserLocationRepository userLocationRepository;

    Logger logger = LogManager.getLogger("GeolocationService");

    public UserLocation getUserLocation(Long userId) throws ApiException {
        Optional<UserLocation> userLocation = userLocationRepository.findById(userId);
        if(userLocation.isPresent()){
            logger.info("get user location: " + userLocation.get().getId());
            return userLocation.get();
        }else{
            logger.warn("User not found");
            throw new ApiException(404,"No location found");
        }
    }


    public UserLocation addUserLocation(UserLocation userLocation) throws ApiException {

        if(userLocation.getId() != null && userLocation.getLastUpdate() != null && userLocation.getLocation() != null){
            userLocationRepository.save(userLocation);
            logger.info("User location saved : "+userLocation.getId());
            return getUserLocation(userLocation.getId());
        }else{
            logger.warn("User location not saved : "+userLocation.getId());
            throw new ApiException(400,"content error");
        }
    }
}
