package esipe.fr.geolocation.repositories;

import esipe.fr.geolocation.entities.InfoTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoTerminalRepository extends JpaRepository<InfoTerminal, Long> {
}
