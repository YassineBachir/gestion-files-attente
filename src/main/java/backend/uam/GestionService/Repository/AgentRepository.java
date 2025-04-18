package backend.uam.GestionService.Repository;

import backend.uam.GestionService.Model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//public interface AgentRepository extends JpaRepository<Agent, Long> {

//    List<Agent> findByLocalisationId(Long localisationId);
//}

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByLocalisationId(Long localisationId);
}

