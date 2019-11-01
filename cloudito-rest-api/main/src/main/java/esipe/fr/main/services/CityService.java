package esipe.fr.main.services;

import esipe.fr.main.entities.City;

import java.util.List;

public interface CityService {
    City getCityById(Integer id);
    void addCity(City city);
    List<City> getAllCities();
    void deleteCity(Integer id);
}
