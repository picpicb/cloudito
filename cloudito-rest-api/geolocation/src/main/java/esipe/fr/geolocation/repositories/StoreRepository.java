package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
