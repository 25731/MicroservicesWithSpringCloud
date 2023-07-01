package com.example.roomservice.SpringCloudStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;
@Configuration
public class ClassForAsyncMethod {
    @Bean
    public Supplier<Message<String>> roomAccessedSupplier() {
        return () -> MessageBuilder.withPayload("Room availability checked").build();
    }
}

