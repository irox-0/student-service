package org.registrationservice.infrastructure.adapter.out.persistence.repository;

import org.registrationservice.domain.model.UniversitySubject;
import org.registrationservice.infrastructure.adapter.out.persistence.entity.ElectiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaElectiveRepository extends JpaRepository<ElectiveEntity, Long> {
    Optional<ElectiveEntity> getElectiveEntitiesBySubject(UniversitySubject subject);
}
