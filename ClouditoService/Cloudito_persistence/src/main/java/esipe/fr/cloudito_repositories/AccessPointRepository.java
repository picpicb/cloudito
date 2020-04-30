package esipe.fr.cloudito_repositories;


import esipe.fr.cloudito_model.AccessPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessPointRepository extends JpaRepository<AccessPoint, Long> {
}
