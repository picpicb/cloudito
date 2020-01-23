package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
