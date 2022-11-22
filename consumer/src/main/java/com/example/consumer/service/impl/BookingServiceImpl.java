package com.example.consumer.service.impl;

import com.example.consumer.dto.BookingDto;
import com.example.consumer.entity.Booking;
import com.example.consumer.entity.TripWayPoint;
import com.example.consumer.mapper.BookingMapper;
import com.example.consumer.mapper.BookingMapperInterface;
import com.example.consumer.repositry.BookingRepository;
import com.example.consumer.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BookingMapperInterface bookingMapperInterface;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper, BookingMapperInterface bookingMapperInterface) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.bookingMapperInterface = bookingMapperInterface;
    }


    @Override
    public String create(String message) throws IOException {
        Booking booking = bookingMapper.toJavaObject(message);
        booking.setCreatedOn(LocalDateTime.now());
        booking.setLastModifiedOn(LocalDateTime.now());
        for (TripWayPoint tripWayPoint : booking.getTripWayPoints()) {
            tripWayPoint.setId(UUID.randomUUID().toString());
        }
        booking = bookingRepository.save(booking);
        BookingDto bookingDto = bookingMapper.entityToDto(booking);
        return bookingMapper.toJSON(bookingDto);
    }


    @Override
    public String update(String message) throws IOException {
        Booking booking = bookingMapper.toJavaObject(message);
        Booking persistBooking = bookingRepository.getById(booking.getId());
        for (TripWayPoint tripWayPoint : booking.getTripWayPoints()) {
            if (tripWayPoint.getId() == null) {
                tripWayPoint.setId(UUID.randomUUID().toString());
            }
        }
        bookingMapperInterface.updateBooking(booking, persistBooking);
        persistBooking.setLastModifiedOn(LocalDateTime.now());
        persistBooking = bookingRepository.save(persistBooking);
        BookingDto bookingDto = bookingMapper.entityToDto(persistBooking);
        return bookingMapper.toJSON(bookingDto);
    }

    @Override
    public String getAll() throws IOException {
        List<Booking> bookingList = bookingRepository.findAll();
        List<BookingDto> bookingDtoList = bookingMapper.entityToDto(bookingList);
        return bookingMapper.toJSON(bookingDtoList);
    }

    @Override
    public String getById(String message) throws Exception {
        String id = bookingMapper.toString(message);
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            BookingDto bookingDto = bookingMapper.entityToDto(booking.get());
            return bookingMapper.toJSON(bookingDto);
        } else {
            return "No booking with such id: " + id;
        }
    }


    @Override
    public void delete(String message) throws IOException {
        String bookingId = bookingMapper.toString(message);
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
        }
    }
}
