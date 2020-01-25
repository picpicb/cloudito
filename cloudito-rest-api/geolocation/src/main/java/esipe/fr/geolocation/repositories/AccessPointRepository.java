package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.AccessPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessPointRepository extends JpaRepository<AccessPoint, Long> {
}
