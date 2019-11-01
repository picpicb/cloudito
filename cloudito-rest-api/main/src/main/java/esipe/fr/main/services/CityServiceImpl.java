package esipe.fr.main.services;

import esipe.fr.main.entities.City;
import esipe.fr.main.repositories.CityRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private CityRepositoryInMemory cityRepository;

    @Autowired
    public CityServiceImpl(final CityRepositoryInMemory cityRepository){
        this.cityRepository = cityRepository;
    }


    @Override
    public City getCityById(Integer id) {
        return this.cityRepository.getCityById(id);
    }

    @Override
    public void addCity(City city) {
        this.cityRepository.addCity(city);
    }

    @Override
    public List<City> getAllCities() {
        return this.cityRepository.getAllCities();
    }

    @Override
    public void deleteCity(Integer id) {
        this.cityRepository.deleteCity(id);
    }
}
