package com.example.travelo_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.travelo_backend.model.Seat;
import com.example.travelo_backend.repository.SeatRepository;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatRepository seatRepository;

    public SeatController(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Map<String, String>> getSeats(@PathVariable Long flightId) {

        Seat seat = seatRepository.findByFlight_IdFlight(flightId)
                .orElseThrow(() -> new RuntimeException("Seats not found for flight " + flightId));

        Map<String, String> data = new HashMap<>();

        data.put("a1", seat.getA1());
        data.put("b1", seat.getB1());
        data.put("c1", seat.getC1());
        data.put("d1", seat.getD1());
        data.put("a2", seat.getA2());
        data.put("b2", seat.getB2());
        data.put("c2", seat.getC2());
        data.put("d2", seat.getD2());
        data.put("a3", seat.getA3());
        data.put("b3", seat.getB3());
        data.put("c3", seat.getC3());
        data.put("d3", seat.getD3());

        return ResponseEntity.ok(data);
    }
}
