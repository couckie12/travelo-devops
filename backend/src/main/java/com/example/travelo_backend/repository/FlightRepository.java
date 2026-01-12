package com.example.travelo_backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelo_backend.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureCityAndArrivalCityAndDate(
            String departureCity,
            String arrivalCity,
            LocalDate date
    );
}
