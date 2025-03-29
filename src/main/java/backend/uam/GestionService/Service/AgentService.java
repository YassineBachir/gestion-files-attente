package backend.uam.GestionService.Service;

import backend.uam.GestionService.Model.Agent;
import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Repository.AgentRepository;
import backend.uam.GestionService.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private LocationRepository locationRepository;

    // Méthode pour récupérer l'agent par localisation
    public Optional<Agent> getAgentByLocalisation(Long localisationId) {
        return agentRepository.findByLocalisationId(localisationId);
    }

    // Méthode pour obtenir le numéro du ticket en cours de traitement
    public int getCurrentProcessingTicket(Long localisationId) {
        Optional<Location> location = locationRepository.findById(localisationId);
        return location.map(Location::getCurrentProcessing).orElse(1);
    }

    // Méthode pour passer au client suivant
    public int processNextTicket(Long localisationId) {
        Optional<Location> locationOpt = locationRepository.findById(localisationId);
        if (locationOpt.isPresent()) {
            Location location = locationOpt.get();
            location.setCurrentProcessing(location.getCurrentProcessing() + 1);
            locationRepository.save(location);
            return location.getCurrentProcessing();
        }
        return 1;
    }

    // Méthode pour revenir au client précédent
    public int processPreviousTicket(Long localisationId) {
        Optional<Location> locationOpt = locationRepository.findById(localisationId);
        if (locationOpt.isPresent()) {
            Location location = locationOpt.get();
            if (location.getCurrentProcessing() > 1) {
                location.setCurrentProcessing(location.getCurrentProcessing() - 1);
                locationRepository.save(location);
            }
            return location.getCurrentProcessing();
        }
        return 1;
    }
}
