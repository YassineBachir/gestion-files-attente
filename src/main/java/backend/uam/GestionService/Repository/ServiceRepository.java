package backend.uam.GestionService.Repository;


import backend.uam.GestionService.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    Service findByName(String name);
}


