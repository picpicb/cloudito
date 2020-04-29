package esipe.fr.repositories;


import esipe.fr.cloudito_model.InfoTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoTerminalRepository extends JpaRepository<InfoTerminal, Long> {
}
