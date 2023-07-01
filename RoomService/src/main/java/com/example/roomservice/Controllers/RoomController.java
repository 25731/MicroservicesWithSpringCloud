package com.example.roomservice.Controllers;

import com.example.roomservice.Entities.Room;
import com.example.roomservice.Entities.CleaningStatus;
import com.example.roomservice.Exception.RoomConflictException;
import com.example.roomservice.Exception.RoomNotFoundException;
import com.example.roomservice.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.function.Supplier;

@RestController
@RequestMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final Supplier<Message<String>> roomAccessedSupplier;

    @GetMapping("/{id}")
    public Mono<Room> getRoom(@PathVariable Long id) {
        return roomService.getRoom(id)
                .onErrorMap(RoomNotFoundException.class, ex -> new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @PostMapping
    public Mono<Room> createRoom(@RequestBody Room room) {
        return roomService.createRoom(room)
                .onErrorMap(RoomConflictException.class, ex -> new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @PutMapping("/{id}")
    public Mono<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room)
                .onErrorMap(RoomNotFoundException.class, ex -> new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id)
                .onErrorMap(RoomNotFoundException.class, ex -> new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @GetMapping("/Available")
    public Flux<Room> getAvailableRooms() {
        roomAccessedSupplier.get();
        return roomService.getRoomsByCleaningStatus(CleaningStatus.NOT_CLEANING);
    }
}