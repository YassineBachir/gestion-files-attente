package backend.uam.GestionService.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference
    private Service service;

    @OneToMany(mappedBy = "localisation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> tickets;

    @OneToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private int currentProcessing;

    public Location() {}

    public Location(String name, Service service) {
        this.name = name;
        this.service = service;
        this.currentProcessing = 1;
    }


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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public int getCurrentProcessing() {
        return currentProcessing;
    }

    public void setCurrentProcessing(int currentProcessing) {
        this.currentProcessing = currentProcessing;
    }
}
