package backend.uam.GestionService.Controller;

import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Model.Service;
import backend.uam.GestionService.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.uam.GestionService.Repository.LocationRepository;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/services")
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/locations")
    public List<Location> getLocations() {
        //You can use this line to get all locations.
        return locationRepository.findAll();

        // If you want to filter by serviceId, you must provide the serviceId
        //  For example, if you want location with serviceId = 1, try:
        // return locationRepository.findByServiceId(1L);
    }
}
