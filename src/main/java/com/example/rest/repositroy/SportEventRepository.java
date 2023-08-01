package com.example.rest.repositroy;

import com.example.rest.models.entities.SportEvent;
import com.example.rest.models.enums.SportStatus;
import com.example.rest.models.enums.SportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SportEventRepository extends JpaRepository<SportEvent, Long>, JpaSpecificationExecutor<SportEvent> {
    List<SportEvent> findAllByStartDate(LocalDate date);
    List<SportEvent> findAllByStartDateBetweenOrderByStartDateAsc(LocalDate start, LocalDate end);
    List<SportEvent> findAllByStartDateAfterOrStartDateEqualsOrderByStartDateAsc(LocalDate start, LocalDate start1);
    List<SportEvent> findAllByStartDateBeforeOrderByStartDateAsc(LocalDate end); // zima site eventi kreirano pred toj datum

    List<SportEvent> findAllBySportType(SportType sportType);
    Optional<SportEvent> findByNameIgnoreCase(String name);

    List<SportEvent> findAllByStatus(SportStatus status);
    Optional<SportEvent> findById(Long id);

//    @Query("SELECT count(LIKED_SPORT_EVENTS_ID ) FROM APP_USERS_LIKED_SPORT_EVENTS LIKED_SPORT_EVENTS_ID WHERE LIKED_SPORT_EVENTS_ID.LIKED_SPORT_EVENTS_ID = :id")
//    Integer findSportEventsByLikes(Long id);

    @Query("SELECT count(likedSportEvents) FROM User user JOIN user.likedSportEvents likedSportEvents WHERE likedSportEvents.id = :id")
    Integer findSportEventsByLikes(Long id);

//    List<SportEvent> findAllBySaved(boolean saved);
}
