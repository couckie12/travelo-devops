package com.example.travelo_backend.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.travelo_backend.model.Flight;
import com.example.travelo_backend.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // ===== CREATE =====
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    // ===== READ ALL =====
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    // ===== READ BY ID =====
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(
            @PathVariable Long id,
            @RequestBody Flight flight) {

        return flightService.updateFlight(id, flight)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    // ===== SEARCH =====
    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String from,
            @RequestParam String to,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate departureDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate returnDate
    ) {

        if (returnDate == null) {
            return ResponseEntity.ok(
                    flightService.searchOneWayFlights(from, to, departureDate)
            );
        }

        Map<String, Object> result = new HashMap<>();
        result.put("oneWayFlights",
                flightService.searchOneWayFlights(from, to, departureDate));
        result.put("returnFlights",
                flightService.searchReturnFlights(to, from, returnDate));

        return ResponseEntity.ok(result);
    }
}
