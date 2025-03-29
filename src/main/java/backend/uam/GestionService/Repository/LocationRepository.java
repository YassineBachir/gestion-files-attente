package backend.uam.GestionService.Repository;


import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    // Récupérer toutes les localisations pour un service spécifique
    List<Location> findByService(Service service);

    List<Location> findByServiceId(Long serviceId);
}


