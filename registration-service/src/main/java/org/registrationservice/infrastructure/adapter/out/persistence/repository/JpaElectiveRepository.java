package org.registrationservice.infrastructure.adapter.out.persistence.repository;

import org.commonlibs.event.UniversitySubject;
import org.registrationservice.infrastructure.adapter.out.persistence.entity.ElectiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface JpaElectiveRepository extends JpaRepository<ElectiveEntity, UUID> {
    Optional<ElectiveEntity> getElectiveEntitiesBySubject(UniversitySubject subject);
}
