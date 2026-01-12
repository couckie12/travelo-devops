package com.example.travelo_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelo_backend.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findByFlight_IdFlight(Long flightId);
}
