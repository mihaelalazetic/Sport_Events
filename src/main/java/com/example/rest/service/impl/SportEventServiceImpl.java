package com.example.rest.service.impl;

import com.example.rest.models.entities.SportEvent;
import com.example.rest.models.entities.User;
import com.example.rest.models.enums.SportStatus;
import com.example.rest.models.enums.SportType;
import com.example.rest.repositroy.SportEventRepository;
import com.example.rest.repositroy.UserRepository;
import com.example.rest.service.SportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SportEventServiceImpl implements SportEventService {
    @Autowired
    private SportEventRepository sportEventRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public SportEvent createOrUpdate(SportEvent sportEvent) {
        // TODO THROW ERROR IF EVENT WITH THAT NAME EXISTS O CREATE
//        if (sportEventRepository.findByNameIgnoreCase(sportEvent.getName()).isPresent()) {
//            throw new RuntimeException("The event with that name already exists");
//        }
        return sportEventRepository.save(sportEvent);
    }

    @Override
    public SportEvent findById(Long id) {
        if (sportEventRepository.findById(id).isPresent()){
            return sportEventRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public String deleteById(Long id) {
        sportEventRepository.deleteById(id);
        return "Successfully deleted!";
    }

    @Override
    public void delete(SportEvent sportEvent) {
        sportEventRepository.delete(sportEvent);
    }

    @Override
    public List<SportEvent> getAll() {
        return sportEventRepository.findAll();
    }

    @Override
    public List<SportEvent> getSportEvents(LocalDate start, LocalDate end, SportStatus status, SportType type, String name, Boolean sort) {//add sort
        List<SportEvent> sportEvents = null;

        //https://stackoverflow.com/questions/45973070/spring-jpa-examplematcher-compare-date-condition

        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAll()
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<SportEvent> example = Example.of(
                SportEvent.builder()
                        .name(name)
                        .status(status)
                        .sportType(type)
                        .build(), ignoringExampleMatcher);
        sportEvents = sportEventRepository.findAll(
                getDatesFilterAndExample(start, end, example),
                Sort.by(sort != null ? Sort.Direction.ASC : Sort.Direction.DESC, "name")
        );
        return sportEvents;
    }

    public Specification<SportEvent> getDatesFilterAndExample(
            LocalDate from,
            LocalDate to,
            Example<SportEvent> example) {

        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (from != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), from));
            }
            if (to != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("startDate"), to));
            }
            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<SportEvent> getAllByDate(LocalDate date) {
        return sportEventRepository.findAllByStartDate(date);
    }

    @Override
    public List<SportEvent> getAllByDateBetween(LocalDate start, LocalDate end) {
        return sportEventRepository.findAllByStartDateBetweenOrderByStartDateAsc(start, end);
    }

    @Override
    public List<SportEvent> getAllBySportType(SportType sportType) {
        return sportEventRepository.findAllBySportType(sportType);
    }

    @Override
    public List<SportEvent> getAllBySportStatus(SportStatus status) {
        return sportEventRepository.findAllByStatus(status);
    }

    @Override
    public List<SportEvent> getAllDesc() {
        return sportEventRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public String likeSportEvent(String username, Long sportEventId) {
        User user = userRepository.findByUsername(username);
        SportEvent sportEvent = sportEventRepository.findAllById(Collections.singleton(sportEventId)).get(0);
        sportEvent.setTotalLikes(getLikesOfEvent(sportEventId));
        sportEventRepository.save(sportEvent);
        if (!user.getLikedSportEvents().contains(sportEvent)) {
            user.getLikedSportEvents().add(sportEvent);
            userRepository.save(user);
        }
        return sportEvent.getName() + " liked";
    }

    public String removeHeartById(String username, Long sportEventId) {
        User user = userRepository.findByUsername(username);
        SportEvent sportEvent = null;
        if (!sportEventRepository.findAllById(Collections.singleton(sportEventId)).isEmpty()){
            sportEvent = sportEventRepository.findAllById(Collections.singleton(sportEventId)).get(0);
        }

        if (user.getLikedSportEvents().contains(sportEvent)) {
            user.getLikedSportEvents().remove(sportEvent);
            userRepository.save(user);
        }
        assert sportEvent != null;
        sportEvent.setTotalLikes(getLikesOfEvent(sportEventId));
        sportEventRepository.save(sportEvent);
        return sportEvent.getName() + " un-liked";
    }

    public List<SportEvent> getAllLikedSportEventsOfUser(String username) {
        User user = userRepository.findByUsername(username);
        return user.getLikedSportEvents();
    }

    @Override
    public Integer getLikesOfEvent(Long id) {
        return sportEventRepository.findSportEventsByLikes(id);
    }
}