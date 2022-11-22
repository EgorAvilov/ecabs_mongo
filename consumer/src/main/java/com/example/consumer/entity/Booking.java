package com.example.consumer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "bookings")
public class Booking {

    @Id
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


    private List<TripWayPoint> tripWayPoints;
}
