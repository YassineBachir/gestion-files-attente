package backend.uam.GestionService.Repository;


import backend.uam.GestionService.Model.Location;
import backend.uam.GestionService.Model.Service;
import backend.uam.GestionService.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {


    List<Location> findByService(Service service);

    List<Location> findByServiceId(Long serviceId);
    //Ticket findByTicketNumberAndLocation(int ticketNumber, Location location);

}


