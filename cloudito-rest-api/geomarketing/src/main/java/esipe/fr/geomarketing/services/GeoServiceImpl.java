package esipe.fr.geomarketing.services;

import esipe.fr.geomarketing.entities.GeoInfo;
import esipe.fr.geomarketing.repositories.GeoRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoServiceImpl implements GeoService {
    private GeoRepositoryInMemory geoCityRepository;

    @Autowired
    public GeoServiceImpl(final GeoRepositoryInMemory geoCityRepository){
        this.geoCityRepository = geoCityRepository;
    }


    @Override
    public GeoInfo getGeoById(Integer id) {
        return this.geoCityRepository.getCityById(id);
    }

    @Override
    public void addGeo(GeoInfo geoInfo) {
        this.geoCityRepository.addCity(geoInfo);
    }

    @Override
    public List<GeoInfo> getAllGeo() {
        return this.geoCityRepository.getAllCities();
    }

    @Override
    public void deleteGeo(Integer id) {
        this.geoCityRepository.deleteCity(id);
    }
}
