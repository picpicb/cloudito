package esipe.fr.Cloudito_repositories;

import esipe.fr.Cloudito_model.MapView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapViewRepository extends JpaRepository<MapView, Long> {
}
