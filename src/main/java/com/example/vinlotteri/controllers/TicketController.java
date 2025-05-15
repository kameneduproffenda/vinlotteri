package com.example.vinlotteri.controllers;

import com.example.vinlotteri.models.PurchaseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final List<Integer> availableTickets = new ArrayList<>();
    public static final Map<Integer, String> soldTickets = new HashMap<>();

    public TicketController() {
        generateTickets();
    }

    public void generateTickets() {
        for (int i = 1; i <= 100; i++) {
            availableTickets.add(i);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Integer>> getAvailableTickets() {
        return ResponseEntity.ok(availableTickets);
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseTickets(@RequestBody PurchaseRequest request) {
        List<Integer> requestedTickets = request.ticketNumbers();

        if (!availableTickets.containsAll(requestedTickets)) {
            return ResponseEntity.badRequest().body("Noen av loddene er allerede solgt!");
        }

        availableTickets.removeAll(requestedTickets);

        for (Integer ticket : requestedTickets) {
            soldTickets.put(ticket, request.buyerName());
        }
        return ResponseEntity.ok("Lodd kjøpt for" + request.buyerName() + ": " + requestedTickets);
    }
}
