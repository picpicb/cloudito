package esipe.fr.repositories;

import esipe.fr.model.CustomerDetection;
import esipe.fr.model.CustomerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetectionRepository extends JpaRepository<CustomerLocation, Long> {
    CustomerDetection findFirstByCustomerIdOrderByLastUpdateDesc(Long customerId);
}
