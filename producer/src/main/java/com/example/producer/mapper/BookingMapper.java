package com.example.producer.mapper;
import com.example.producer.dto.BookingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Mapper
@NoArgsConstructor
public  class BookingMapper {
    public String toJSON(BookingDto bookingDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(bookingDto);
    }


    public String toJSON(String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(id);
    }
}
