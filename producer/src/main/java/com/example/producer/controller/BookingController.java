package com.example.producer.controller;

import com.example.producer.dto.BookingDto;
import com.example.producer.mapper.BookingMapper;
import com.example.producer.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Log4j2
@RestController
@RequestMapping(value = "/api/bookings")
public class BookingController {
    private final RabbitTemplate template;
    private final BookingMapper mapper;

    @Autowired
    public BookingController(RabbitTemplate template, BookingMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BookingDto bookingDto) throws IOException {
        template.setExchange(Constants.MESSAGE_EXCHANGE);
        Object ob = template.convertSendAndReceive(Constants.CREATE_BOOKING_KEY, mapper.toJSON(bookingDto));
        return new ResponseEntity<>(ob, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity update(@RequestBody BookingDto bookingDto) throws IOException {
        template.setExchange(Constants.MESSAGE_EXCHANGE);
        return new ResponseEntity<>(template.convertSendAndReceive(Constants.UPDATE_BOOKING_KEY, mapper.toJSON(bookingDto)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) throws IOException {
        template.setExchange(Constants.MESSAGE_EXCHANGE);
        template.convertAndSend(Constants.DELETE_BOOKING_KEY, mapper.toJSON(id));
    }

    @GetMapping
    public ResponseEntity getAll() throws IOException {
        template.setExchange(Constants.MESSAGE_EXCHANGE);
        return new ResponseEntity<>(template.convertSendAndReceive(Constants.GET_ALL_BOOKINGS_KEY, mapper.toJSON("get all bookings")), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable String id) throws IOException {
        template.setExchange(Constants.MESSAGE_EXCHANGE);
        return new ResponseEntity<>(template.convertSendAndReceive(Constants.GET_BY_ID_BOOKING_KEY, mapper.toJSON(id)), HttpStatus.OK);
    }
}
