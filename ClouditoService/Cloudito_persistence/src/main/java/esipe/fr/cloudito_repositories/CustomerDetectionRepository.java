package esipe.fr.cloudito_repositories;

import esipe.fr.cloudito_model.CustomerDetection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetectionRepository extends JpaRepository<CustomerDetection, Long> {
}
