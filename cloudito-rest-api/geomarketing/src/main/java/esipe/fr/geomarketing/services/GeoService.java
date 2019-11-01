package esipe.fr.geomarketing.services;

import esipe.fr.geomarketing.entities.GeoInfo;

import java.util.List;

public interface GeoService {
    GeoInfo getGeoById(Integer id);
    void addGeo(GeoInfo geoInfo);
    List<GeoInfo> getAllGeo();
    void deleteGeo(Integer id);
}
