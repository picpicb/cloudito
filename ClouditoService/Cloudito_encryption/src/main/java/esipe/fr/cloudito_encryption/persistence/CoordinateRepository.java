package esipe.fr.cloudito_encryption.persistence;



import esipe.fr.cloudito_encryption.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}
