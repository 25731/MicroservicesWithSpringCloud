package com.example.analitycs.AsyncConsumerClassMethod;

import com.example.analitycs.Entities.RoomCheck;
import com.example.analitycs.Repos.RoomCheckRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class MessagingConfiguration {
    private final RoomCheckRepository roomCheckRepository;
    @Bean
    public Consumer<Message<String>> roomAccessedConsumer() {
        return message -> {
            RoomCheck roomCheck = new RoomCheck();
            roomCheck.setMessage(message.getPayload());
            roomCheckRepository.save(roomCheck).subscribe();
        };
    }
}