//package backend.uam.GestionService.Controller;
//import backend.uam.GestionService.Model.Service;
//import backend.uam.GestionService.Repository.ServiceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@Controller
//
//public class ServiceController {
//
//    @Autowired
//    private ServiceRepository serviceRepository;
//
//    @GetMapping("/")
//    public String showServices(Model model) {
//        List<Service> services = serviceRepository.findAll();
//        model.addAttribute("services", services);
//        return "index"; // Affiche index.jsp
//    }
//
//    @GetMapping("/service/{id}")
//    public String showServicePage(@PathVariable("id") Long id, Model model) {
//        Service service = serviceRepository.findById(id).orElse(null);
//        if (service != null) {
//            model.addAttribute("service", service);
//            model.addAttribute("localisations", service.getLocalisations());
//        }
//        return "service"; // Affiche service.jsp
//    }
//}


package backend.uam.GestionService.Controller;

import backend.uam.GestionService.Model.Service;
import backend.uam.GestionService.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/")
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable("id") Long id) {
        return serviceRepository.findById(id).orElse(null);
    }
}
