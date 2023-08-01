package com.example.rest.controller;

import com.example.rest.config.SecurityUtils;
import com.example.rest.models.entities.SportEvent;
import com.example.rest.models.enums.SportStatus;
import com.example.rest.models.enums.SportType;
import com.example.rest.repositroy.UserRepository;
import com.example.rest.service.SportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api/sportEvent")
public class SportEventController {

    @Autowired // avto inicializacija na promenliva
    private SportEventService service;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/signUp")
    public String signUp(Model model) {
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/getList")
    public String getListOfAllEvents(Model model) {
        List<SportEvent> allSportEvents = service.getAll();
        allSportEvents.forEach(sportEvent -> {
            if(sportEvent.getEndDate().isBefore(LocalDate.now())){
                sportEvent.setStatus(SportStatus.COMPLETED);
            } else if (sportEvent.getStartDate().isBefore(LocalDate.now())
                    && sportEvent.getEndDate().isAfter(LocalDate.now())) {
                sportEvent.setStatus(SportStatus.ACTIVE);
            } else if (sportEvent.getStartDate().isBefore(LocalDate.now()) &&
                    sportEvent.getEndDate().isBefore(LocalDate.now())) {
                sportEvent.setStatus(SportStatus.NOT_STARTED);
            }
        });
        allSportEvents.forEach(sportEvent -> sportEvent.setTotalLikes(service.getLikesOfEvent(sportEvent.getId())));
        model.addAttribute("events", allSportEvents);
        String user = SecurityUtils.getCurrentUsername();
        model.addAttribute("user", userRepository.findByUsername(user));
        model.addAttribute("statuses", SportStatus.getSportStatusValues());
        model.addAttribute("types", SportType.getSportTypeValues());
        model.addAttribute("sportEvent", new SportEvent());
        return "list";
    }

    @PostMapping("/create")
    public String createEvent(Model model, @ModelAttribute SportEvent sportEvent) {
//        model.addAttribute("created", service.createOrUpdate(sportEvent));
        SportEvent sportEvent1 = service.createOrUpdate(sportEvent);
        return "redirect:/api/sportEvent/getList";
    }

    @PostMapping("/delete")
    public String deleteEvent(Model model, Long id) {
        service.deleteById(id);
        return "redirect:/api/sportEvent/getList";
    }

    @PostMapping("/removeHeart")
    public String removeHeartEvent(Model model, Long id, String username,String where) {
        service.removeHeartById(username, id);
        if (Objects.equals(where, "sportEvent")){
            return "redirect:/api/sportEvent/view/"+id;
        }else {
            return "redirect:/api/sportEvent/getList";
        }
    }

    @PostMapping("/heart")
    public String heartEvent(Model model, Long id, String username,String where) {
        service.likeSportEvent(username, id);
        if (Objects.equals(where, "sportEvent")){
            return "redirect:/api/sportEvent/view/"+id;
        }else {
            return "redirect:/api/sportEvent/getList";
        }
    }

    @GetMapping("/getHearts")
    public String getListOfSavedEvents(Model model) {
        String username = SecurityUtils.getCurrentUsername();
        List<SportEvent> allSportEvents = service.getAllLikedSportEventsOfUser(username);
        allSportEvents.forEach(sportEvent -> sportEvent.setTotalLikes(service.getLikesOfEvent(sportEvent.getId())));
        model.addAttribute("events", allSportEvents);
        model.addAttribute("user", userRepository.findByUsername(username));
        model.addAttribute("statuses", SportStatus.getSportStatusValues());
        model.addAttribute("types", SportType.getSportTypeValues());
        model.addAttribute("sportEvent", new SportEvent());
        return "list";
    }

    @GetMapping("/getByStatus")
    public String getAllBySportStatus(Model model, @ModelAttribute SportStatus sportStatus) {
        model.addAttribute("events", service.getAllBySportStatus(sportStatus));
        return "redirect:/api/sportEvent/getList";
    }

    @GetMapping("/sort")
    public String getListOfAllEventsSort(Model model,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                         @RequestParam(required = false) SportStatus status,
                                         @RequestParam(required = false) SportType sportType,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) Boolean sort) {
        List<SportEvent> sportEvents = service.getSportEvents(start, end, status, sportType, name, sort);
        sportEvents.forEach(sportEvent -> sportEvent.setTotalLikes(service.getLikesOfEvent(sportEvent.getId())));
        model.addAttribute("events", sportEvents);
        String username = SecurityUtils.getCurrentUsername();
        model.addAttribute("user", userRepository.findByUsername(username));
        model.addAttribute("statuses", SportStatus.getSportStatusValues());
        model.addAttribute("types", SportType.getSportTypeValues());
        model.addAttribute("sportEvent", new SportEvent());
        return "list";
    }

    @GetMapping("/view/{eventId}")
    public String viewEvent(Model model, @PathVariable Long eventId) {

        SportEvent sportEvent = service.findById(eventId);
        if (sportEvent != null) {
            sportEvent.setTotalLikes(service.getLikesOfEvent(sportEvent.getId()));
            String username = SecurityUtils.getCurrentUsername();
            model.addAttribute("user", userRepository.findByUsername(username));
            model.addAttribute("event", sportEvent);
            model.addAttribute("statuses", SportStatus.getSportStatusValues());
            model.addAttribute("types", SportType.getSportTypeValues());
            model.addAttribute("sportEvent", new SportEvent());
            return "sportEvent";
        }else {
            throw new RuntimeException("Sport Event does not exist");
        }

    }


}
