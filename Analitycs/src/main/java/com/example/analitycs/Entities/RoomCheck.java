package com.example.analitycs.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Table
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class RoomCheck {
    @Id
    private Long id;
    private String message;
}
