package esipe.fr.cloudito_repositories;


import esipe.fr.cloudito_model.CustomerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLocationRepository extends JpaRepository<CustomerLocation, Long> {
    CustomerLocation findFirstByCustomerIdOrderByLastUpdateDesc(Long customerId);
}
