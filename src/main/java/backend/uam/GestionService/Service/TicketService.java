//////package backend.uam.GestionService.Service;
//////
//////import backend.uam.GestionService.Model.Location;
//////import backend.uam.GestionService.Model.Ticket;
//////import backend.uam.GestionService.Repository.LocationRepository;
//////import backend.uam.GestionService.Repository.TicketRepository;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import java.util.List;
//////import java.util.Optional;
//////
//////@Service
//////public class TicketService {
//////
//////    @Autowired
//////    private TicketRepository ticketRepository;
//////
//////    @Autowired
//////    private LocationRepository localisationRepository;
//////
//////    // Générer un ticket pour une localisation donnée
//////    public Ticket generateTicket(Long localisationId) {
//////        Location localisation = localisationRepository.findById(localisationId)
//////                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//////
//////        // Récupérer tous les tickets pour cette localisation
//////        List<Ticket> tickets = ticketRepository.findByLocalisation(localisation);
//////
//////        int nextTicketNumber = 1;
//////        int position = 1; // Position par défaut pour un nouveau ticket
//////        int peopleAhead = 0; // Nombre de personnes devant
//////
//////        if (!tickets.isEmpty()) {
//////            // Si des tickets existent déjà, on prend le dernier ticket
//////            Ticket lastTicket = tickets.get(tickets.size() - 1); // Dernier ticket
//////            nextTicketNumber = lastTicket.getTicketNumber() + 1; // Numéro du prochain ticket
//////            position = lastTicket.getPosition() + 1; // Position du prochain ticket
//////            peopleAhead = lastTicket.getTicketNumber(); // Nombre de personnes devant
//////        }
//////
//////        // Numéro en cours de traitement (peut être mis à jour séparément)
//////        int currentProcessing = localisation.getCurrentProcessing();
//////
//////        // Créer et enregistrer le nouveau ticket
//////        Ticket ticket = new Ticket(localisation, nextTicketNumber, position, peopleAhead, currentProcessing);
//////        return ticketRepository.save(ticket);
//////    }
//////
//////
//////
//////    // Obtenir le ticket en cours de traitement pour une localisation donnée
//////    public Ticket getCurrentTicket(Long localisationId) {
//////        Location localisation = localisationRepository.findById(localisationId)
//////                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//////
//////        int currentProcessing = localisation.getCurrentProcessing();
//////
//////        // Vérifier si un ticket est en cours de traitement
//////        return ticketRepository.findByTicketNumberAndLocalisation(currentProcessing, localisation);
//////    }
//////
//////    // Passer au client suivant
//////    public void nextClient(Long localisationId) {
//////        Location localisation = localisationRepository.findById(localisationId)
//////                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//////
//////        localisation.setCurrentProcessing(localisation.getCurrentProcessing() + 1);
//////        localisationRepository.save(localisation);
//////    }
//////
//////    // Revenir au client précédent
//////    public void previousClient(Long localisationId) {
//////        Location localisation = localisationRepository.findById(localisationId)
//////                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//////
//////        if (localisation.getCurrentProcessing() > 1) {
//////            localisation.setCurrentProcessing(localisation.getCurrentProcessing() - 1);
//////            localisationRepository.save(localisation);
//////        }
//////    }
//////}
////package backend.uam.GestionService.Service;
////
////import backend.uam.GestionService.Model.Location;
////import backend.uam.GestionService.Model.Ticket;
////import backend.uam.GestionService.Repository.LocationRepository;
////import backend.uam.GestionService.Repository.TicketRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////import java.util.concurrent.atomic.AtomicInteger;
////
////@Service
////public class TicketService {
////
////    private final AtomicInteger ticketCounter = new AtomicInteger(0);
////    private final AtomicInteger currentProcessingTicket = new AtomicInteger(1);
////
////    @Autowired
////    private TicketRepository ticketRepository;
////
////    @Autowired
////    private LocationRepository localisationRepository;
////
////    // Générer un ticket pour une localisation donnée
////    public Ticket generateTicket(Long localisationId) {
////        Location localisation = localisationRepository.findById(localisationId)
////                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
////
////        // Initialiser le compteur de tickets si nécessaire
////        if (ticketCounter.get() == 0) {
////            List<Ticket> existingTickets = ticketRepository.findByLocalisation(localisation);
////            if (!existingTickets.isEmpty()) {
////                int maxTicketNumber = existingTickets.stream()
////                        .mapToInt(Ticket::getTicketNumber)
////                        .max()
////                        .orElse(0);
////                ticketCounter.set(maxTicketNumber);
////            }
////        }
////
////        int ticketNumber = ticketCounter.incrementAndGet(); // Incrémente et obtient le numéro du ticket
////
////        // Position et nombre de personnes devant
////        int position = ticketNumber;
////        int peopleAhead = ticketNumber - currentProcessingTicket.get();
////
////        // Numéro en cours de traitement (peut être mis à jour séparément)
////        int currentProcessing = currentProcessingTicket.get();
////
////        // Créer et enregistrer le nouveau ticket
////        Ticket ticket = new Ticket(localisation, ticketNumber, position, peopleAhead, currentProcessing);
////        return ticketRepository.save(ticket);
////    }
////
////    // Obtenir le ticket en cours de traitement pour une localisation donnée
////    public Ticket getCurrentTicket(Long localisationId) {
////        Location localisation = localisationRepository.findById(localisationId)
////                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
////
////        int currentProcessing = currentProcessingTicket.get();
////
////        // Vérifier si un ticket est en cours de traitement
////        return ticketRepository.findByTicketNumberAndLocalisation(currentProcessing, localisation);
////    }
////
////    // Passer au client suivant
////    public void nextClient() {
////        currentProcessingTicket.incrementAndGet();
////    }
////
////    // Revenir au client précédent
////    public void previousClient() {
////        currentProcessingTicket.set(Math.max(1, currentProcessingTicket.get() - 1));
////    }
////
////    // Obtenir la position dans la file
////    public int getPositionInQueue(int ticketNumber) {
////        return ticketNumber - currentProcessingTicket.get();
////    }
////
////    // Obtenir le nombre de personnes devant
////    public int getPeopleAhead(int ticketNumber) {
////        return Math.max(0, getPositionInQueue(ticketNumber));
////    }
////
////    // Obtenir le ticket en cours de traitement
////    public int getCurrentProcessingTicket() {
////        return currentProcessingTicket.get();
////    }
////}
//
//package backend.uam.GestionService.Service;
//
//import backend.uam.GestionService.Model.Location;
//import backend.uam.GestionService.Model.Ticket;
//import backend.uam.GestionService.Repository.LocationRepository;
//import backend.uam.GestionService.Repository.TicketRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TicketService {
//
//    @Autowired
//    private TicketRepository ticketRepository;
//
//    @Autowired
//    private LocationRepository localisationRepository;
//
//    // Générer un ticket pour une localisation donnée
//    public Ticket generateTicket(Long localisationId) {
//        Location localisation = localisationRepository.findById(localisationId)
//                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//
//        // Récupérer le dernier ticket enregistré pour cette localisation
////        Ticket lastTicket = ticketRepository.findTopByLocalisationOrderByTicketNumberDesc(localisation);
//        Ticket lastTicket = ticketRepository.findLastTicketByLocalisation(localisation);
//
//        int nextTicketNumber = (lastTicket != null) ? lastTicket.getTicketNumber() + 1 : 1;
//
//
//
//        int position = nextTicketNumber;
//        int peopleAhead = Math.max(0, nextTicketNumber - localisation.getCurrentProcessing());
//        int currentProcessing = localisation.getCurrentProcessing();
//
//        Ticket ticket = new Ticket(localisation, nextTicketNumber, position, peopleAhead, currentProcessing);
//        return ticketRepository.save(ticket);
//    }
//
//
//    // Obtenir le ticket en cours de traitement pour une localisation donnée
//    public Ticket getCurrentTicket(Long localisationId) {
//        Location localisation = localisationRepository.findById(localisationId)
//                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//
//        int currentProcessing = localisation.getCurrentProcessing();
//
//        // Vérifier si un ticket est en cours de traitement
//        return ticketRepository.findByTicketNumberAndLocalisation(currentProcessing, localisation);
//    }
//
//    // Passer au client suivant
//    public void nextClient(Long localisationId) {
//        // Récupérer la localisation
//        Location localisation = localisationRepository.findById(localisationId)
//                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//
//        // Incrémenter le numéro en cours de traitement
//        int nextProcessing = localisation.getCurrentProcessing() + 1;
//        localisation.setCurrentProcessing(nextProcessing);
//
//        // Sauvegarder la localisation mise à jour
//        localisationRepository.save(localisation);
//        System.out.println("Client suivant, numéro en cours de traitement: " + nextProcessing);
//
//    }
//
//
//    // Revenir au client précédent
//    public void previousClient(Long localisationId) {
//        // Récupérer la localisation
//        Location localisation = localisationRepository.findById(localisationId)
//                .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));
//
//        // Décrémenter le numéro en cours de traitement (ne pas aller en dessous de 1)
//        if (localisation.getCurrentProcessing() > 1) {
//            int prevProcessing = localisation.getCurrentProcessing() - 1;
//            localisation.setCurrentProcessing(prevProcessing);
//
//            // Sauvegarder la localisation mise à jour
//            localisationRepository.save(localisation);
//
//            System.out.println("Client précédent, numéro en cours de traitement: " + prevProcessing);
//        }
//    }
//
//
//}

package backend.uam.GestionService.Service;

import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Model.Ticket;
import backend.uam.GestionService.Repository.LocationRepository;
import backend.uam.GestionService.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    private final LocationRepository locationRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(LocationRepository locationRepository, TicketRepository ticketRepository) {
        this.locationRepository = locationRepository;
        this.ticketRepository = ticketRepository;
    }


    public Ticket generateTicket(Long locationId) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));

        Optional<Ticket> lastTicketOpt = ticketRepository.findTopByLocalisationOrderByTicketNumberDesc(location);
        int nextTicketNumber = lastTicketOpt.map(ticket -> ticket.getTicketNumber() + 1).orElse(1);

        int position = nextTicketNumber;
        int peopleAhead = Math.max(0, nextTicketNumber - location.getCurrentProcessing());


        int currentProcessing = location.getCurrentProcessing();

        Ticket ticket = new Ticket(location, nextTicketNumber, position, peopleAhead, currentProcessing);

        ticketRepository.save(ticket);


        location.setCurrentProcessing(nextTicketNumber);
        locationRepository.save(location);

        return ticket;
    }


    public Ticket getCurrentTicket(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));

        return ticketRepository.findTopByLocalisationOrderByTicketNumberDesc(location)
                .orElseThrow(() -> new RuntimeException("Aucun ticket trouvé"));
    }


    public void nextClient(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));

        location.setCurrentProcessing(location.getCurrentProcessing() + 1);
        locationRepository.save(location);
    }


    public void previousClient(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));

        if (location.getCurrentProcessing() > 1) {
            location.setCurrentProcessing(location.getCurrentProcessing() - 1);
            locationRepository.save(location);
        }
    }


}
