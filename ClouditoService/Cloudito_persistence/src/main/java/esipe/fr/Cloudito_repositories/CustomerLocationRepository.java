package esipe.fr.Cloudito_repositories;


import esipe.fr.Cloudito_model.CustomerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLocationRepository extends JpaRepository<CustomerLocation, Long> {
    CustomerLocation findFirstByCustomerIdOrderByLastUpdateDesc(Long customerId);
}
