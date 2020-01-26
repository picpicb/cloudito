package esipe.fr.geolocation.services;



import esipe.fr.geolocation.exceptions.ApiException;
import esipe.fr.model.AccessPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import esipe.fr.repositories.AccessPointRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AccessPointService {

    @Autowired
    AccessPointRepository accessPointRepository;
    private Logger logger = LogManager.getLogger("AccessPointService");

    public AccessPoint addAccessPoints(AccessPoint accessPoint) throws ApiException {
        if(accessPoint.getId() != null && accessPoint.getLocation() != null
                && accessPoint.getMac() != null
                && accessPoint.getSsid() != null){
            accessPointRepository.save(accessPoint);
            logger.info("New AP saved : "+accessPoint.getId());
            return getAccessPoint(accessPoint.getId());
        }else{
            logger.warn("AP not saved : "+accessPoint.getId());
            throw new ApiException(400,"content error");
        }
    }


    public AccessPoint getAccessPoint(long id) throws ApiException {
        Optional<AccessPoint> accessPoint = accessPointRepository.findById(id);
        if(accessPoint.isPresent()){
            logger.info("get AP: " + accessPoint.get().getId());
            return accessPoint.get();
        }else{
            logger.warn("AP not found");
            throw new ApiException(404,"No AP found");
        }
    }


    public List<AccessPoint> getAllAccessPoints(){
        return accessPointRepository.findAll();
    }

}
