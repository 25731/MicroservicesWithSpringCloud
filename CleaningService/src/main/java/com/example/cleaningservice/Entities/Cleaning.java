package com.example.cleaningservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cleaning {

    @Id
    private Long id;

    private Long roomId;

    private LocalDate cleaningDate;
}