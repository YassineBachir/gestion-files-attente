package backend.uam.GestionService.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "localisation_id")
    @JsonBackReference // Empêche la boucle infinie avec Location
    private Location localisation;

//    private int ticketNumber;
    private Integer ticketNumber;

    private int position;
    private int peopleAhead;
    private int currentProcessing;

    public Ticket() {}

    public Ticket(Location localisation, int ticketNumber, int position, int peopleAhead, int currentProcessing) {
        this.localisation = localisation;
        this.ticketNumber = ticketNumber;
        this.position = position;
        this.peopleAhead = peopleAhead;
        this.currentProcessing = currentProcessing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Location localisation) {
        this.localisation = localisation;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPeopleAhead() {
        return peopleAhead;
    }

    public void setPeopleAhead(int peopleAhead) {
        this.peopleAhead = peopleAhead;
    }

    public int getCurrentProcessing() {
        return currentProcessing;
    }

    public void setCurrentProcessing(int currentProcessing) {
        this.currentProcessing = currentProcessing;
    }
}

