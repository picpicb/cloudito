package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
}
