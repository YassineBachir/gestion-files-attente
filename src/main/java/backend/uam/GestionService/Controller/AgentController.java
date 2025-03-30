package backend.uam.GestionService.Controller;

import backend.uam.GestionService.Model.Agent;
import backend.uam.GestionService.Model.Ticket;
import backend.uam.GestionService.Service.AgentService;
import backend.uam.GestionService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "http://localhost:3000")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{localisationId}")
    public ResponseEntity<?> getCurrentTicket(@PathVariable Long localisationId) {
        try {

            //  Ticket currentTicket = agentService.getCurrentTicket(localisationId); //This line was causing the error
            Optional<Agent> agent = agentService.getAgentByLocalisation(localisationId);

            if (agent.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            int currentProcessingTicket = agentService.getCurrentProcessingTicket(localisationId);

            return ResponseEntity.ok().body(Map.of(
                    "currentProcessingTicket", currentProcessingTicket,
                    "nomAgent", agent.get().getName(),
                    "nomLocalisation", agent.get().getLocalisation().getName()
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{localisationId}/next")
    public ResponseEntity<Void> nextClient(@PathVariable Long localisationId) {
        agentService.processNextTicket(localisationId); // Use AgentService
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{localisationId}/previous")
    public ResponseEntity<Void> previousClient(@PathVariable Long localisationId) {
        try {
            agentService.processPreviousTicket(localisationId);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
//http://localhost:8080/api/agent/1