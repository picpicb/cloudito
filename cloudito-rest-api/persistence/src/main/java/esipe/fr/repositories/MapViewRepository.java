package esipe.fr.repositories;

import esipe.fr.model.MapView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapViewRepository extends JpaRepository<MapView, Long> {
}
