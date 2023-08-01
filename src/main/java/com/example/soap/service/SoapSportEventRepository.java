package com.example.soap.service;

import com.example.soap.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SoapSportEventRepository {

    private static final Map<String, SportEvent> sportEvents = new HashMap<>();

    @PostConstruct
    public void initData() {
        // initialize countries map
        SportEvent fifa = new SportEvent();
        fifa.setId(1);
        fifa.setName("FIFA World Cup");
        fifa.setSportType(SportType.SOCCER);
        fifa.setStatus(Status.ACTIVE);
        fifa.setStartDate(String.valueOf(LocalDate.of(2022, 11, 21)));
        fifa.setEndDate(String.valueOf(LocalDate.of(2022, 11, 21)));
        fifa.setLocation("Qatar");

        sportEvents.put(fifa.getName(), fifa);

        SportEvent fifa1 = new SportEvent();
        fifa1.setId(2);
        fifa1.setName("Tennis");
        fifa1.setSportType(SportType.MULTI_SPORT);
        fifa1.setStatus(Status.COMPLETED);
        fifa1.setStartDate(String.valueOf(LocalDate.of(2022, 11, 21)));
        fifa1.setEndDate(String.valueOf(LocalDate.of(2022, 11, 21)));
        fifa1.setLocation("Lebanon");


        sportEvents.put(fifa1.getName(), fifa1);

        SportEvent fifa2 = new SportEvent();
        fifa2.setId(3);
        fifa2.setName("ssdsdcs");
        fifa2.setSportType(SportType.HOCKEY);
        fifa2.setStatus(Status.INCOMPLETE);
        fifa2.setStartDate(String.valueOf(LocalDate.of(2022, 11, 21)));
        fifa2.setEndDate(String.valueOf(LocalDate.of(2022, 11, 21)));
        fifa2.setLocation("Qatar");


        sportEvents.put(fifa2.getName(), fifa2);
    }

    public SportEvent findSportEvent(String name) {
        return sportEvents.get(name);
    }
    public List<SportEvent> findSportEventByName(String name) {
        List<SportEvent> sa = new ArrayList<>();
        sportEvents.forEach((s, sportEvent) -> {
            if (sportEvent.getName().contains(name)){
                sa.add(sportEvent);
            }
        });
        return sa;
    }

    public void removeSportEventById(Long id) {
        SportEvent sportEventToRemove = null;
        for (SportEvent sportEvent : sportEvents.values()) {
            if (sportEvent.getId() == id) {
                sportEventToRemove = sportEvent;
                break;
            }
        }
        if (sportEventToRemove != null) {
            sportEvents.remove(sportEventToRemove.getName());
        }
    }

    public List<SportEvent> getSportEvents() {
        List<SportEvent> sportEvents1 = new ArrayList<>();
        sportEvents.forEach((s, sportEvent) -> sportEvents1.add(sportEvent));
        return sportEvents1;
    }

    public List<SportEvent> findByStatus(Status status) {
        List<SportEvent> sportEvents1 = new ArrayList<>();
        sportEvents.forEach((s, sportEvent) -> {
            if (sportEvent.getStatus().toString().equals(status.toString())) {
                sportEvents1.add(sportEvent);
            }
        });
        sportEvents1.forEach(sportEvent -> System.out.println(sportEvent.getName()));
        return sportEvents1;
    }

    public String addSportEvent(SportEventDTO sportEventDTO) {
        Assert.notNull(sportEventDTO.getName(), "Sport event '" + sportEventDTO.getName() + "' already exists!");
        String status;
        if (sportEvents.get(sportEventDTO.getName()) != null) {
            status = "Sport event '" + sportEventDTO.getName() + "' already exists!";
        } else {
            SportEvent sportEvent = new SportEvent();
            sportEvent.setName(sportEventDTO.getName());
            sportEvent.setSportType(sportEventDTO.getSportType());
            sportEvent.setLocation(sportEventDTO.getLocation());
            sportEvent.setStartDate(sportEventDTO.getStartDate());
            sportEvent.setEndDate(sportEventDTO.getEndDate());
            sportEvent.setStatus(sportEventDTO.getStatus());
            sportEvent.setId(4);
            sportEvents.put(sportEventDTO.getName(), sportEvent);
            status = "Success";
        }
        return status;
    }

    public List<SportEvent> getByLocation(String location) {
        List<SportEvent> sportEvents1 = new ArrayList<>();
        sportEvents.forEach((s, sportEvent) -> {
            if (sportEvent.getLocation().contains(location)) {
                sportEvents1.add(sportEvent);
            }
        });
        Assert.notNull(sportEvents1,"Sport event with location '" +  location +"' not found!");
        return sportEvents1;
    }
}
