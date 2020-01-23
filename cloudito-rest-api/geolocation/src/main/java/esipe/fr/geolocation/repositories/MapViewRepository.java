package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.MapView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapViewRepository extends JpaRepository<MapView, Long> {
}
