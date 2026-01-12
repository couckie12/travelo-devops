package com.example.travelo_backend.controller;

import java.lang.reflect.Field;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travelo_backend.model.Flight;
import com.example.travelo_backend.model.Passenger;
import com.example.travelo_backend.model.Reservation;
import com.example.travelo_backend.model.Seat;
import com.example.travelo_backend.repository.FlightRepository;
import com.example.travelo_backend.repository.PassengerRepository;
import com.example.travelo_backend.repository.ReservationRepository;
import com.example.travelo_backend.repository.SeatRepository;

import dto.ReservationDto;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    public ReservationController(PassengerRepository passengerRepository, FlightRepository flightRepository,
                                  SeatRepository seatRepository, ReservationRepository reservationRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }
    @PostMapping("/reserve")
public ResponseEntity<?> reserveSeat(@RequestBody ReservationDto dto) {

    // 1️⃣ نجيب Seat باستعمال flightId
    Seat seat = seatRepository.findByFlight_IdFlight(dto.getFlightId())
            .orElseThrow(() -> new RuntimeException("Seats not found for this flight"));

    // 2️⃣ نتحقق من seat
    String seatName = dto.getSeatName().toLowerCase();

    try {
        Field field = Seat.class.getDeclaredField(seatName);
        field.setAccessible(true);

        String status = (String) field.get(seat);

        if ("reserved".equalsIgnoreCase(status)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Seat already reserved");
        }

        // 3️⃣ نبدّل الحالة
        field.set(seat, "reserved");
        seatRepository.save(seat);

    } catch (Exception e) {
        return ResponseEntity
                .badRequest()
                .body("Invalid seat name");
    }

    // 4️⃣ Passenger
    Passenger passenger = passengerRepository.save(dto.getPassenger());

    // 5️⃣ Flight
    Flight flight = flightRepository.findById(dto.getFlightId())
            .orElseThrow(() -> new RuntimeException("Flight not found"));

    // 6️⃣ نحفظ Reservation
    Reservation reservation = new Reservation();
    reservation.setPassenger(passenger);
    reservation.setFlight(flight);
    reservation.setSeat(seatName);

    reservationRepository.save(reservation);

    return ResponseEntity.ok("Reservation successful");
}
    public PassengerRepository getPassengerRepository() {
        return passengerRepository;
    }
    public FlightRepository getFlightRepository() {
        return flightRepository;
    }
    public SeatRepository getSeatRepository() {
        return seatRepository;
    }
    public ReservationRepository getReservationRepository() {
        return reservationRepository;
    }
}
