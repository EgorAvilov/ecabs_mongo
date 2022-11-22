package com.example.consumer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("passengerName")
    private String passengerName;

    @JsonProperty("passengerContactNumber")
    private String passengerContactNumber;

    @JsonProperty("pickupTime")
    @JsonFormat(pattern = "uuuu-MM-dd'T'HH:mm:ss")
    private LocalDateTime pickupTime;

    @JsonProperty("asap")
    private Boolean asap;

    @JsonProperty("waitingTime")
    @JsonFormat(pattern = "uuuu-MM-dd'T'HH:mm:ss")
    private LocalDateTime waitingTime;

    @JsonProperty("numberOfPassengers")
    private Integer numberOfPassengers;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("createdOn")
    @JsonFormat(pattern = "uuuu-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonProperty("lastModifiedOn")
    @JsonFormat(pattern = "uuuu-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedOn;

    @JsonProperty("tripWayPoints")
    private List<TripWayPointDto> tripWayPoints;
}
