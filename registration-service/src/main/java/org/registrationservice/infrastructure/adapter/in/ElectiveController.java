package org.registrationservice.infrastructure.adapter.in;

import lombok.RequiredArgsConstructor;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.port.in.ElectiveManagementPort;
import org.registrationservice.infrastructure.adapter.in.dto.ElectiveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/elective")
@RequiredArgsConstructor
public class ElectiveController {
    private final ElectiveManagementPort electiveManagementPort;

    @PostMapping("create")
    public void create(@RequestBody ElectiveRequestDto dto) {
        Elective elective = electiveManagementPort.create(dto.subject());
    }

}
