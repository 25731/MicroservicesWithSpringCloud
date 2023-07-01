package com.example.cleaningservice.Controllers;

import com.example.cleaningservice.Entities.*;
import com.example.cleaningservice.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/cleaning")
@RequiredArgsConstructor
public class CleaningController {

    private final CleaningService cleaningService;

    @PostMapping
    public Mono<Cleaning> createCleaning(@RequestParam Long roomId) {
        return cleaningService.createCleaning(roomId)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An issue occurred while creating the cleaning task.", e)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Room with the provided ID not found.")));
    }

    @GetMapping("/{id}")
    public Mono<Cleaning> getCleaning(@PathVariable Long id) {
        return cleaningService.getCleaning(id)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An issue occurred while retrieving the cleaning task.", e)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cleaning task with the provided ID not found.")));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCleaning(@PathVariable Long id) {
        return cleaningService.deleteCleaning(id)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An issue occurred while deleting the cleaning task.", e)))
                .then(Mono.error(new ResponseStatusException(HttpStatus.OK, "Cleaning task with the provided ID successfully deleted.")));
    }
}