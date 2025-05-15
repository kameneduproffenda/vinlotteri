package com.example.vinlotteri.controllers;

import com.example.vinlotteri.models.DrawResult;
import com.example.vinlotteri.models.Wine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/lottery")
public class LotteryController {

    private final List<Wine> wines = List.of(
            new Wine("Vin 1", 150),
            new Wine("Vin 2", 250),
            new Wine("Vin 3", 300),
            new Wine("Vin 4", 500),
            new Wine("Vin 5", 800)
    );

    private final Map<Integer, String> soldTickets = TicketController.soldTickets;

    @PostMapping("/draw")
    public ResponseEntity<List<DrawResult>> drawWinners() {
        if (soldTickets.isEmpty()) {
            return ResponseEntity.badRequest().body(List.of());
        }

        List<DrawResult> results = new ArrayList<>();
        List<Map.Entry<Integer, String>> entries = new ArrayList<>(soldTickets.entrySet());

        Collections.shuffle(entries);

        List<Wine> sortedWines = new ArrayList<>(wines);
        sortedWines.sort(Comparator.comparingDouble(Wine::getPrice));

        int drawCount = Math.min(sortedWines.size(), entries.size());

        for (int i = 0; i < drawCount; i++) {
            Map.Entry<Integer, String> winnerEntry = entries.get(i);
            Wine wine = sortedWines.get(i);

            results.add(new DrawResult(
                    wine.getName(),
                    wine.getPrice(),
                    winnerEntry.getValue(),
                    winnerEntry.getKey()
            ));
        }

        return ResponseEntity.ok(results);
    }
}
