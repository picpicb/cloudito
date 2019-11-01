package esipe.fr.main.repositories;

import esipe.fr.main.entities.City;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepositoryInMemory {
    private ArrayList<City> cities;

    public CityRepositoryInMemory(){
        this.cities = new ArrayList<>();
    }

    public City getCityById(Integer id){
        for (City c : this.cities) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void addCity(City city){
        this.cities.add(city);
    }

    public List<City> getAllCities(){
        return this.cities;
    }

    public void deleteCity(Integer id) {
        this.cities.remove(getCityById(id));
    }
}
