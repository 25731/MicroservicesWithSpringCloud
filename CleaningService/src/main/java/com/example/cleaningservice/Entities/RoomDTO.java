package com.example.cleaningservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private CleaningStatus cleaningStatus;
}