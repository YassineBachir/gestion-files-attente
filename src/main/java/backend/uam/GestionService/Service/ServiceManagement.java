package backend.uam.GestionService.Service;

import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Model.Ticket;
import backend.uam.GestionService.Repository.LocationRepository;
import backend.uam.GestionService.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceManagement {

    private final LocationRepository locationRepository;
    private final TicketRepository ticketRepository;
    private final Map<String, Integer> ticketCounters = new HashMap<>();

    @Autowired
    public ServiceManagement(LocationRepository locationRepository, TicketRepository ticketRepository) {
        this.locationRepository = locationRepository;
        this.ticketRepository = ticketRepository;
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

    private String generateKey(Long locationId) {
        return "loc-" + locationId;
    }
}
