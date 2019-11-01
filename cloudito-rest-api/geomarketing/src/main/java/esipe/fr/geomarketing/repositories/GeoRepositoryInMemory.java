package esipe.fr.geomarketing.repositories;

import esipe.fr.geomarketing.entities.GeoInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GeoRepositoryInMemory {
    private ArrayList<GeoInfo> cities;

    public GeoRepositoryInMemory(){
        this.cities = new ArrayList<>();
    }

    public GeoInfo getCityById(Integer id){
        for (GeoInfo c : this.cities) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void addCity(GeoInfo geoInfo){
        this.cities.add(geoInfo);
    }

    public List<GeoInfo> getAllCities(){
        return this.cities;
    }

    public void deleteCity(Integer id) {
        this.cities.remove(getCityById(id));
    }
}
