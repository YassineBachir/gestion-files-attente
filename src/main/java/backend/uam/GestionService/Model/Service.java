package backend.uam.GestionService.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int currentProcessingTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location localisation;

    public Service() {}

    public Service(String name, int currentProcessingTicket, Location localisation) {
        this.name = name;
        this.currentProcessingTicket = currentProcessingTicket;
        this.localisation = localisation;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentProcessingTicket() {
        return currentProcessingTicket;
    }

    public void setCurrentProcessingTicket(int currentProcessingTicket) {
        this.currentProcessingTicket = currentProcessingTicket;
    }

    public Location getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Location localisation) {
        this.localisation = localisation;
    }
}
