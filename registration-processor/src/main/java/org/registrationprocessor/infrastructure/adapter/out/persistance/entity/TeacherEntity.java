package org.registrationprocessor.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.commonlibs.event.UniversitySubject;

import java.time.LocalDateTime;

@Entity
@Table(name = "teachers")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "subject")
    @Enumerated(EnumType.STRING)
    private UniversitySubject subject;

    @Column(name = "possible_time")
    private LocalDateTime possibleTime;
}
