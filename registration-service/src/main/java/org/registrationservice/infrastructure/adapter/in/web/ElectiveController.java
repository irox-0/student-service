package org.registrationservice.infrastructure.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.UniversitySubject;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.port.in.ElectiveManagementPort;
import org.registrationservice.infrastructure.adapter.in.web.dto.ElectiveEventResponseDto;
import org.registrationservice.infrastructure.adapter.in.web.dto.ElectiveRequestDto;
import org.registrationservice.infrastructure.adapter.in.web.dto.ElectiveResponseDto;
import org.registrationservice.infrastructure.adapter.in.web.mapper.ElectiveDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/elective")
@RequiredArgsConstructor
@Slf4j
public class ElectiveController {
    private final ElectiveManagementPort electiveManagementPort;
    private final ElectiveDtoMapper mapper;

    @PostMapping("create")
    public ResponseEntity<ElectiveResponseDto> createElective(@RequestBody ElectiveRequestDto dto) {
        Elective elective = electiveManagementPort.create(dto.subject());
        return new ResponseEntity<>(mapper.toResponseDto(elective), HttpStatus.CREATED);
    }

    @GetMapping("get/available-subjects")
    public ResponseEntity<List<UniversitySubject>> getSubjects() {
        return new ResponseEntity<>(List.of(UniversitySubject.values()), HttpStatus.OK);
    }

    @GetMapping("get/by-subject/{subject}")
    public ResponseEntity<ElectiveEventResponseDto> getElectiveEvent(@PathVariable String subject) {
        UniversitySubject enumSubject = UniversitySubject.valueOf(subject.toUpperCase());
        var optionalElective = electiveManagementPort.get(enumSubject);
        return optionalElective.map(elective ->
                new ResponseEntity<>(mapper.toEventResponseDto(elective), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
