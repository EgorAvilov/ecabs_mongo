package com.example.consumer.mapper;

import com.example.consumer.entity.Booking;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookingMapperInterface {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "booking.passengerName", target = "passengerName")
    @Mapping(source = "booking.passengerContactNumber", target = "passengerContactNumber")
    @Mapping(source = "booking.pickupTime", target = "pickupTime")
    @Mapping(source = "booking.asap", target = "asap")
    @Mapping(source = "booking.waitingTime", target = "waitingTime")
    @Mapping(source = "booking.numberOfPassengers", target = "numberOfPassengers")
    @Mapping(source = "booking.price", target = "price")
    @Mapping(source = "booking.rating", target = "rating")
    @Mapping(source = "booking.tripWayPoints", target = "tripWayPoints")
    void updateBooking(Booking booking, @MappingTarget Booking persistBooking);
}
