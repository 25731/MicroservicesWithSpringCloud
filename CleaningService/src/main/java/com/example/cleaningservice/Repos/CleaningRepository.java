package com.example.cleaningservice.Repos;

import com.example.cleaningservice.Entities.Cleaning;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CleaningRepository extends ReactiveCrudRepository<Cleaning, Long> {
}