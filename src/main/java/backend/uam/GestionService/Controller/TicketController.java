package backend.uam.GestionService.Controller;

import backend.uam.GestionService.Model.Ticket;
import backend.uam.GestionService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/generate/{localisationId}")
    public Ticket generateTicket(@PathVariable Long localisationId) {
        return ticketService.generateTicket(localisationId);
    }

    @GetMapping("/current/{localisationId}")
    public Ticket getCurrentTicket(@PathVariable Long localisationId) {
        return ticketService.getCurrentTicket(localisationId);
    }

    @PostMapping("/next/{localisationId}")
    public void nextClient(@PathVariable Long localisationId) {
        ticketService.nextClient(localisationId); // Passe localisationId
    }

    @PostMapping("/previous/{localisationId}")
    public void previousClient(@PathVariable Long localisationId) {
        ticketService.previousClient(localisationId); // Passe localisationId
    }
}
