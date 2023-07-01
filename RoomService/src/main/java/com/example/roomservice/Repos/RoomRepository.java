package com.example.roomservice.Repos;

import com.example.roomservice.Entities.CleaningStatus;
import com.example.roomservice.Entities.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {
    Flux<Room> findByCleaningStatus(CleaningStatus status);
}