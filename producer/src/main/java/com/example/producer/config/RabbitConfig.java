package com.example.producer.config;

import com.example.producer.utils.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class RabbitConfig {
    private static final String CONNECTION_NAME = "rabbitmq";

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(CONNECTION_NAME);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue bookingCreateQueue() {
        return new Queue(Constants.BOOKING_CREATE_QUEUE_NAME);
    }

    @Bean
    public Queue bookingUpdateQueue() {
        return new Queue(Constants.BOOKING_UPDATE_QUEUE_NAME);
    }

    @Bean
    public Queue bookingDeleteQueue() {
        return new Queue(Constants.BOOKING_DELETE_QUEUE_NAME);
    }
    @Bean
    public Queue bookingGetAllQueue() {
        return new Queue(Constants.BOOKING_GET_ALL_QUEUE_NAME);
    }
    @Bean
    public Queue bookingGetByIdQueue() {
        return new Queue(Constants.BOOKING_GET_BY_ID_QUEUE_NAME);
    }

    @Bean
    public Queue messageAuditQueue() {
        return new Queue(Constants.MESSAGE_AUDIT_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange messageExchange() {
        return new FanoutExchange(Constants.MESSAGE_EXCHANGE);
    }

    @Bean
    public DirectExchange bookingExchange() {
        return new DirectExchange(Constants.BOOKING_EXCHANGE);
    }

    @Bean
    public Binding BookingExchangeToMessageExchange() {
        return bind(bookingExchange()).to(messageExchange());
    }

    @Bean
    public Binding BookingCreateQueueToBookingExchange() {
        return bind(bookingCreateQueue()).to(bookingExchange())
                .with(Constants.CREATE_BOOKING_KEY);
    }

    @Bean
    public Binding BookingUpdateQueueToBookingExchange() {
        return bind(bookingUpdateQueue()).to(bookingExchange())
                .with(Constants.UPDATE_BOOKING_KEY);
    }

    @Bean
    public Binding BookingDeleteQueueToBookingExchange() {
        return bind(bookingDeleteQueue()).to(bookingExchange())
                .with(Constants.DELETE_BOOKING_KEY);
    }

    @Bean
    public Binding BookingGetAllQueueToBookingExchange() {
        return bind(bookingGetAllQueue()).to(bookingExchange())
                .with(Constants.GET_ALL_BOOKINGS_KEY);
    }

    @Bean
    public Binding BookingGetByIdQueueToBookingExchange() {
        return bind(bookingGetByIdQueue()).to(bookingExchange())
                .with(Constants.GET_BY_ID_BOOKING_KEY);
    }

    @Bean
    public Binding MessageAuditQueueToMessageExchange() {
        return bind(messageAuditQueue()).to(messageExchange());
    }
}
