package com.example.consumer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
public class TripWayPoint {
    @Id
    private String id;


    private String locality;


    private Double latitude;


    private Double longitude;
}
