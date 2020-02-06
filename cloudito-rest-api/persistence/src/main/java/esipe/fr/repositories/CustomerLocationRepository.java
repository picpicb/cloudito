package esipe.fr.repositories;


import esipe.fr.model.CustomerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLocationRepository extends JpaRepository<CustomerLocation, Long> {
    CustomerLocation findFirstByCustomerIdOrderByLastUpdateDesc(Long customerId);
}
