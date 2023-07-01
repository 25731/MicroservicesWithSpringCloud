package com.example.cleaningservice.Service;

import com.example.cleaningservice.Components.RoomServiceClient;
import com.example.cleaningservice.Entities.Cleaning;
import com.example.cleaningservice.Repos.CleaningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CleaningService {

    private final CleaningRepository cleaningRepository;
    private final RoomServiceClient roomServiceClient;

    public Mono<Cleaning> createCleaning(Long roomId) {
        return roomServiceClient.getAvailableRooms()
                .filter(roomDto -> roomDto.getId().equals(roomId))
                .next()
                .flatMap(roomDto -> {
                    Cleaning cleaning = new Cleaning();
                    cleaning.setRoomId(roomId);
                    cleaning.setCleaningDate(LocalDate.now());
                    return cleaningRepository.save(cleaning);
                });
    }

    public Mono<Cleaning> getCleaning(Long id) {
        return cleaningRepository.findById(id);
    }

    public Mono<Void> deleteCleaning(Long id) {
        return cleaningRepository.deleteById(id);
    }
}