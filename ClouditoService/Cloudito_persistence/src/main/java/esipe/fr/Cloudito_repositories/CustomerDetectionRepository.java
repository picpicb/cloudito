package esipe.fr.Cloudito_repositories;

import esipe.fr.Cloudito_model.CustomerDetection;
import esipe.fr.Cloudito_model.CustomerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetectionRepository extends JpaRepository<CustomerLocation, Long> {
    CustomerDetection findFirstByCustomerIdOrderByLastUpdateDesc(Long customerId);
}
