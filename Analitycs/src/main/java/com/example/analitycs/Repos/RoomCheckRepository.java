package com.example.analitycs.Repos;

import com.example.analitycs.Entities.RoomCheck;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomCheckRepository extends ReactiveCrudRepository<RoomCheck, Long> {
}