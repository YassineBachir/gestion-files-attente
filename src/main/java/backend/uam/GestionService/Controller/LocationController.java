//package backend.uam.GestionService.Controller;
//
//import backend.uam.GestionService.Model.Location;
//import backend.uam.GestionService.Repository.LocationRepository;
//import backend.uam.GestionService.Service.TicketService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//
//@RequestMapping("/api/locations")
//public class LocationController {
//
//    @Autowired
//    private LocationRepository locationRepository;
//
//    @Autowired
//    private TicketService ticketService;
//
//    @GetMapping("/service/{serviceId}")
//    public ResponseEntity<?> getLocationsByService(@PathVariable Long serviceId) {
//        try {
//            List<Location> locations = locationRepository.findByServiceId(serviceId);
//
//            if(locations.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            return ResponseEntity.ok(locations);
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body("Erreur lors de la récupération des localisations: " + e.getMessage());
//        }
//    }
//
//    @PostMapping("/{id}/generate-ticket")
//    public ResponseEntity<?> generateTicketForLocation(@PathVariable Long id) {
//        try {
//            Optional<Location> location = locationRepository.findById(id);
//
//            if(!location.isPresent()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            return ResponseEntity.ok(ticketService.generateTicket(id));
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body("Erreur de génération du ticket: " + e.getMessage());
//        }
//    }
//}

package backend.uam.GestionService.Controller;

import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Repository.LocationRepository;
import backend.uam.GestionService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<?> getLocationsByService(@PathVariable Long serviceId) {
        try {
            List<Location> locations = locationRepository.findByServiceId(serviceId);

            if(locations.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(locations);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erreur lors de la récupération des localisations: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/generate-ticket")
    public ResponseEntity<?> generateTicketForLocation(@PathVariable Long id) {
        try {
            Optional<Location> location = locationRepository.findById(id);

            if(!location.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(ticketService.generateTicket(id));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erreur de génération du ticket: " + e.getMessage());
        }
    }
}
