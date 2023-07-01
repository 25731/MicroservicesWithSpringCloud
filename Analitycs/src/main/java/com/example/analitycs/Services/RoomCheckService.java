package com.example.analitycs.Services;

import com.example.analitycs.Entities.RoomCheck;
import com.example.analitycs.Repos.RoomCheckRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RoomCheckService {
    private final RoomCheckRepository roomCheckRepository;

    public Mono<RoomCheck> saveCheck(RoomCheck roomCheck) {
        return roomCheckRepository.save(roomCheck);
    }

    public Mono<Long> countChecks() {
        return roomCheckRepository.count();
    }
}