

//import backend.uam.GestionService.Model.Location;
//import backend.uam.GestionService.Model.Ticket;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface TicketRepository extends JpaRepository<Ticket, Long> {
//    // Méthode pour récupérer le dernier ticket par localisation
//    Optional<Ticket> findTopByLocationOrderByTicketNumberDesc(Location localisation);
//
//    // Méthode pour récupérer un ticket par son numéro et sa localisation
//    Ticket findByTicketNumberAndLocation(int ticketNumber, Location localisation);
//}





package backend.uam.GestionService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.uam.GestionService.Model.Ticket;
import backend.uam.GestionService.Model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {


    @Query("SELECT COALESCE(MAX(t.ticketNumber), 0) FROM Ticket t WHERE t.localisation = :localisation")
    int findMaxTicketNumberByLocalisation(@Param("localisation") Location localisation);

    Optional<Ticket> findTopByLocalisationOrderByTicketNumberDesc(Location localisation);

    // Recherche d'un ticket par son numéro pour une localisation
    Ticket findByTicketNumberAndLocalisation(int ticketNumber, Location localisation);

}
