package com.example.rest.service;

import com.example.rest.models.entities.SportEvent;
import com.example.rest.models.enums.SportStatus;
import com.example.rest.models.enums.SportType;

import java.time.LocalDate;
import java.util.List;

public interface SportEventService {
    SportEvent createOrUpdate(SportEvent sportEvent);
    SportEvent findById(Long id);
    String deleteById(Long id);
    void delete(SportEvent sportEvent);
    List<SportEvent> getAll();

//    List<SportEvent> getSportEvents(LocalDate start, LocalDate end);

    List<SportEvent> getSportEvents(LocalDate start, LocalDate end, SportStatus sportStatus, SportType sportType, String name, Boolean sort);

    List<SportEvent> getAllByDate(LocalDate date);

    List<SportEvent> getAllByDateBetween(LocalDate start, LocalDate end);

    List<SportEvent> getAllBySportType(SportType sportType);

    List<SportEvent> getAllBySportStatus(SportStatus status);

    List<SportEvent> getAllDesc();
    String likeSportEvent(String username, Long sportEventId);
    String removeHeartById(String username, Long sportEventId);
    List<SportEvent> getAllLikedSportEventsOfUser(String username);
    Integer getLikesOfEvent(Long id);
}
