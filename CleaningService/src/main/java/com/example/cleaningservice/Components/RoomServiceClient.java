package com.example.cleaningservice.Components;

import com.example.cleaningservice.Entities.RoomDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class RoomServiceClient {

    private final WebClient webClient;

    public RoomServiceClient() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8072")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }
    public Flux<RoomDTO> getAvailableRooms() {
        return webClient.get()
                .uri("/rooms/Available")
                .retrieve()
                .bodyToFlux(RoomDTO.class);
    }
}