package com.example.rest.controller;

import com.example.rest.models.entities.SportEvent;
import com.example.rest.models.enums.SportStatus;
import com.example.rest.models.enums.SportType;
import com.example.rest.service.SportEventService;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rest/sportEvent")
public class SportEventControllerRest {
    @Autowired // avto za inicializacija na promenliva
    private SportEventService service;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public SportEvent createEvent(SportEvent sportEvent) {
        return service.createOrUpdate(sportEvent);
    }

    @PostMapping("/delete")
    public String deleteEvent(Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/getList")
    public List<SportEvent> getListOfAllEvents() {
        return service.getAll();
    }

    @GetMapping("/getByDate")
    public List<SportEvent> getListByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getAllByDate(date);
    }

    @GetMapping("/getBetweenDates")
    public List<SportEvent> getListBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getAllByDateBetween(start, end);
    }

    @GetMapping("/getByType")
    public List<SportEvent> getListBySportType(@RequestParam SportType type) {
        return service.getAllBySportType(type);
    }

    @GetMapping("/getByStatus")
    public List<SportEvent> getListBySportStatus(@RequestParam SportStatus status) {
        return service.getAllBySportStatus(status);
    }

    @GetMapping("/getDesc")
    public List<SportEvent> getListDesc() {
        return service.getAllDesc();
    }

    @PostMapping("/edit")
    public SportEvent editSportEvent(SportEvent sportEvent) {
        return service.createOrUpdate(sportEvent);
    }

    @GetMapping("/getHearts")
    public List<SportEvent> getHeartedEvents(String username) {
        return service.getAllLikedSportEventsOfUser(username);
    }

    @PostMapping("/heart")
    public String heartSportEvent(Long id,String username) {
        return service.likeSportEvent(username,id);
    }

    @PostMapping("/removeHearts")
    public String removeHeartSportEvent(Long id,String username) {
        return service.removeHeartById(username,id);
    }

    @GetMapping("/sort")
    public List<SportEvent> getSorted(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                      @RequestParam(required = false) SportStatus status,
                                      @RequestParam(required = false) SportType sportType,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) Boolean sort) {
        return service.getSportEvents(start, end, status, sportType, name, sort);
    }

    @GetMapping("/getTotalLikes")
    public Integer getTotalLikes(Long id) {
        return service.getLikesOfEvent(id);
    }
}
