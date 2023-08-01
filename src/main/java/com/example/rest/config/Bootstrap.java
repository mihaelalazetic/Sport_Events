package com.example.rest.config;

import com.example.rest.models.entities.SportEvent;
import com.example.rest.models.enums.Role;
import com.example.rest.models.enums.SportStatus;
import com.example.rest.models.enums.SportType;
import com.example.rest.service.SportEventService;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class Bootstrap {

    @Autowired
    private SportEventService sportEventService;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        userService.register("test", "123", "123", "Test", "Korisnik", Role.ROLE_USER);
        userService.register("test2", "123", "123", "Test", "Korisnik 2", Role.ROLE_USER);
        userService.register("admin", "admin", "admin", "Admin", "Korisnik", Role.ROLE_ADMIN);
        if (sportEventService.getAll().size() == 0) {
            sportEventService.createOrUpdate(
                    SportEvent.builder()
                            .name("FIFA World Cup")
                            .sportType(SportType.SOCCER)
                            .status(SportStatus.COMPLETED)
                            .startDate(LocalDate.of(2022, 11, 21))
                            .endDate(LocalDate.of(2022, 12, 18))
                            .location("Qatar")
                            .description("The FIFA World Cup Qatar 2022™ was played from 20 November to 18 December 2022. 32 teams competed across 64 matches in the 22nd edition of the tournament.")
                            .build()
            );
            sportEventService.createOrUpdate(
                    SportEvent.builder()
                            .name("Winter Olympics")
                            .sportType(SportType.MULTI_SPORT)
                            .status(SportStatus.COMPLETED)
                            .startDate(LocalDate.of(2022, 2, 4))
                            .endDate(LocalDate.of(2022, 2, 20))
                            .location("Beijing, China")
                            .description("The Winter Olympic Games is a major international multi-sport event held once every four years for sports practiced on snow and ice. The first Winter Olympic Games, the 1924 Winter Olympics, were held in Chamonix, France. ")
                            .build()
            );
            sportEventService.createOrUpdate(
                    SportEvent.builder()
                            .name("Australian Open")
                            .sportType(SportType.TENNIS)
                            .status(SportStatus.COMPLETED)
                            .startDate(LocalDate.of(2022, 1, 17))
                            .endDate(LocalDate.of(2022, 1, 30))
                            .location("Melbourne, Australia")
                            .description("The Australian Open is a tennis tournament held annually at Melbourne Park in Melbourne, Victoria, Australia. The tournament is the first of the four Grand Slam tennis events held each year, preceding the French Open, Wimbledon, and the US Open.")
                            .build()
            );

            sportEventService.createOrUpdate(
                    SportEvent.builder()
                            .name("Special Olympics World Summer Games")
                            .sportType(SportType.MULTI_SPORT)
                            .status(SportStatus.ACTIVE)
                            .startDate(LocalDate.of(2023, 6, 16))
                            .endDate(LocalDate.of(2023, 6, 25))
                            .location("Berlin, Germany")
                            .description("The Special Olympics organization is the world’s largest sports organization for children and adults with any intellectual disability. This organization has 4.4 million athletes in 170 different countries around the globe. For over 40 years this organization has been spreading the message that people with intellectual disabilities can succeed if given a chance on a proper platform.")
                            .build()
            );
            sportEventService.createOrUpdate(
                    SportEvent.builder()
                            .name("TEST EVENT")
                            .sportType(SportType.MULTI_SPORT)
                            .status(SportStatus.ACTIVE)
                            .startDate(LocalDate.of(2023, 6, 16))
                            .endDate(LocalDate.of(2023, 6, 19))
                            .location("Skopje, Macedonia")
                            .description("The Special Olympics organization is the world’s largest sports organization for children and adults with any intellectual disability. This organization has 4.4 million athletes in 170 different countries around the globe. For over 40 years this organization has been spreading the message that people with intellectual disabilities can succeed if given a chance on a proper platform.")
                            .build()
            );
        }

    }
}
