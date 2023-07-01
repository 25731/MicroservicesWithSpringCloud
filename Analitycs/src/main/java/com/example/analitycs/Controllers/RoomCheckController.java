package com.example.analitycs.Controllers;

import com.example.analitycs.Services.RoomCheckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class RoomCheckController {
    private final RoomCheckService roomCheckService;
    @GetMapping("/count")
    public Mono<String> getCheckCount() {
        return roomCheckService.countChecks()
                .map(count -> "Od początku działalności, nasi klienci sprawdzali dostępność pokoju " + count + " razy.");
    }
}