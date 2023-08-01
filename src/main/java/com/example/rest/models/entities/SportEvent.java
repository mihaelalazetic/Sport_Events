package com.example.rest.models.entities;

import com.example.rest.models.enums.SportStatus;
import com.example.rest.models.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity // objekt/tabela vo baza
@Data // toString, Getter Setter
@Builder // ClassName.builder().build(); kreira prazen objekt, izmegu builder() i build() moze da stavime
         // bilo koe ime na properties od klasata primer .id(123L) za da dademe vrednost na odreden property
@AllArgsConstructor
@NoArgsConstructor
public class SportEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 5000)
    private String name;

    @Enumerated(EnumType.STRING)
    private SportType sportType;

    @Enumerated(EnumType.ORDINAL)
    private SportStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private String location;

    @Column(length = 5000)
    private String description;

    private Integer totalLikes;
}
