package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.MapView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapViewRepository extends JpaRepository<MapView, Long> {
}
