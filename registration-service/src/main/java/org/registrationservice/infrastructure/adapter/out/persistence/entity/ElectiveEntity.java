package org.registrationservice.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.commonlibs.event.ElectiveStatus;
import org.commonlibs.event.UniversitySubject;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "electives")
public class ElectiveEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "subject", unique = true)
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
