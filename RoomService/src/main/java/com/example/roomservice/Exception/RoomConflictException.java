package com.example.roomservice.Exception;
public class RoomConflictException extends RuntimeException {
    public RoomConflictException(Long id) {
        super("Room with id " + id + " already exists");
    }
}