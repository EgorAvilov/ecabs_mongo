package com.example.producer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {
    @JsonProperty("id")
    private String id;


    private String passengerName;


    private String passengerContactNumber;


    private LocalDateTime pickupTime;


    private Boolean asap;


    private LocalDateTime waitingTime;


    private Integer numberOfPassengers;


    private BigDecimal price;


    private Double rating;


    private LocalDateTime createdOn;


    private LocalDateTime lastModifiedOn;


    private List<TripWayPointDto> tripWayPoints;
}