package com.example.travelo_backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelo_backend.model.Flight;
import com.example.travelo_backend.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Optional<Flight> updateFlight(Long id, Flight flight) {
        return flightRepository.findById(id).map(existing -> {
            flight.setIdFlight(id);
            return flightRepository.save(flight);
        });
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> searchOneWayFlights(
            String from, String to, LocalDate date) {
        return flightRepository
                .findByDepartureCityAndArrivalCityAndDate(from, to, date);
    }

    public List<Flight> searchReturnFlights(
            String from, String to, LocalDate date) {
        return flightRepository
                .findByDepartureCityAndArrivalCityAndDate(from, to, date);
    }
}
