package org.registrationservice.infrastructure.adapter.out.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.UniversitySubject;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.port.out.ElectiveRepository;
import org.registrationservice.infrastructure.adapter.out.persistence.entity.ElectiveEntity;
import org.registrationservice.infrastructure.adapter.out.persistence.mapper.ElectiveEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaElectiveRepositoryAdapter implements ElectiveRepository {
    private final JpaElectiveRepository repository;
    private final ElectiveEntityMapper mapper;

    @Override
    public Elective save(Elective elective) {
        log.info("Elective to save: {}", elective);
        ElectiveEntity entity = mapper.toEntity(elective);
        ElectiveEntity saved = repository.save(entity);
        log.info("ElectiveEntity saved: {}", saved);
        return mapper.toModel(saved);
    }

    @Override
    public Optional<Elective> read(UniversitySubject subject) {
        var optionalEntity = repository.getElectiveEntitiesBySubject(subject);
        return optionalEntity.map(mapper::toModel);
    }
}
