package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.CustomerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLocationRepository extends JpaRepository<CustomerLocation, Long> {
}
