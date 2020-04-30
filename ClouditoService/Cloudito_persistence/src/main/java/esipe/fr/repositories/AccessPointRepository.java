package esipe.fr.repositories;


import esipe.fr.model.AccessPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessPointRepository extends JpaRepository<AccessPoint, Long> {
}
