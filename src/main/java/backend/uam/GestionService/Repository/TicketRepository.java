package backend.uam.GestionService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.uam.GestionService.Model.Ticket;
import backend.uam.GestionService.Model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Récupérer le plus grand numéro de ticket pour une localisation donnée
    @Query("SELECT COALESCE(MAX(t.ticketNumber), 0) FROM Ticket t WHERE t.localisation = :localisation")
    int findMaxTicketNumberByLocalisation(@Param("localisation") Location localisation);

    @Query("SELECT t FROM Ticket t WHERE t.localisation = :localisation ORDER BY t.ticketNumber DESC LIMIT 1")
    Ticket findLastTicketByLocalisation(@Param("localisation") Location localisation);

//    // Rechercher tous les tickets d'une localisation
//    List<Ticket> findByLocalisation(Location localisation);

    // Rechercher un ticket par son numéro pour une localisation
    Ticket findByTicketNumberAndLocalisation(int ticketNumber, Location localisation);

    // Récupérer le dernier ticket créé pour une localisation donnée
    Ticket findTopByLocalisationOrderByTicketNumberDesc(Location localisation);
}
