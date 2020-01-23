package esipe.fr.geolocation.services;

import esipe.fr.geolocation.entities.AccessPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessPointService implements ServiceImpl<AccessPoint> {
    @Override
    public AccessPoint save(AccessPoint accessPoint) {
        return null;
    }

    @Override
    public Optional<AccessPoint> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(AccessPoint accessPoint) {

    }

    @Override
    public List<AccessPoint> findAll() {
        return null;
    }
}
