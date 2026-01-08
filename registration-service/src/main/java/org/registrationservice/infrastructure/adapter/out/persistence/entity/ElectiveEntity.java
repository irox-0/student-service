package org.registrationservice.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.registrationservice.domain.model.ElectiveStatus;
import org.registrationservice.domain.model.UniversitySubject;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "electives")
public class ElectiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject")
    @Enumerated(EnumType.STRING)
    private UniversitySubject subject;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ElectiveStatus status;

}
