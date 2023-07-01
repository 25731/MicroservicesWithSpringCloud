package com.example.roomservice.Services;

import com.example.roomservice.Entities.Room;
import com.example.roomservice.Exception.RoomConflictException;
import com.example.roomservice.Exception.RoomNotFoundException;
import com.example.roomservice.Repos.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.roomservice.Entities.CleaningStatus;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Mono<Room> getRoom(Long id) {
        return roomRepository.findById(id)
                .switchIfEmpty(Mono.error(new RoomNotFoundException(id)));
    }

    public Mono<Room> createRoom(Room room) {
        return roomRepository.findById(room.getId())
                .flatMap(existingRoom -> Mono.<Room>error(new RoomConflictException(room.getId())))
                .switchIfEmpty(roomRepository.save(room));
    }

    public Mono<Room> updateRoom(Long id, Room room) {
        return roomRepository.findById(id)
                .flatMap(existingRoom -> {
                    existingRoom.setRoomType(room.getRoomType());
                    existingRoom.setPricePerNight(room.getPricePerNight());
                    existingRoom.setStatus(room.getStatus());
                    existingRoom.setCleaningStatus(room.getCleaningStatus());
                    return roomRepository.save(existingRoom);
                })
                .switchIfEmpty(Mono.error(new RoomNotFoundException(id)));
    }

    public Mono<Void> deleteRoom(Long id) {
        return roomRepository.findById(id)
                .flatMap(roomRepository::delete)
                .switchIfEmpty(Mono.error(new RoomNotFoundException(id)));
    }

    public Flux<Room> getRoomsByCleaningStatus(CleaningStatus status) {
        return roomRepository.findByCleaningStatus(status);
    }
}