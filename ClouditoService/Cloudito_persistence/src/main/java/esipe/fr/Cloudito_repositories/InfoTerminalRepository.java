package esipe.fr.Cloudito_repositories;


import esipe.fr.Cloudito_model.InfoTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoTerminalRepository extends JpaRepository<InfoTerminal, Long> {
}
