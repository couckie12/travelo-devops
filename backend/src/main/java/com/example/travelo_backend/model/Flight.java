package com.example.travelo_backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private Long idFlight;

    private String departureCity;
    private String departureAirport;
    private String arrivalCity;
    private String arrivalAirport;


@Column(name = "date")
private LocalDate date;


    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;

    private String company;
    private BigDecimal price;

    // ===== GETTERS & SETTERS =====
    public Long getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Long idFlight) {
        this.idFlight = idFlight;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

 public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}


    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
