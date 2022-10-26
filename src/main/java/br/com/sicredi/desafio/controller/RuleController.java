package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.controller.request.RuleRequest;
import br.com.sicredi.desafio.controller.request.StartingSessionRequest;
import br.com.sicredi.desafio.controller.response.RuleResponse;
import br.com.sicredi.desafio.mapper.RuleSessionMapper;
import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.mapper.RuleMapper;
import br.com.sicredi.desafio.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rules")
public class RuleController {

    private final RuleService ruleService;

    private final RuleMapper ruleMapper;

    private final RuleSessionMapper ruleSessionMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RuleResponse> getRule(@PathVariable Long id) {
        MDC.put("correlation_id", UUID.randomUUID().toString());
        return ResponseEntity.ok(ruleMapper.dtoToResponse(ruleService.get(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RuleResponse> createRule(@RequestBody RuleRequest request) {
        MDC.put("correlation_id", UUID.randomUUID().toString());
        RuleDto dto = ruleService.create(ruleMapper.requestToDto(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ruleMapper.dtoToResponse(dto));
    }

    @PostMapping(path = "/{id}/start-session", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> getRule(@PathVariable Long id, @RequestBody StartingSessionRequest startingSession) {
        MDC.put("correlation_id", UUID.randomUUID().toString());
        RuleDto dto = ruleService.startVotingSession(id, ruleSessionMapper.requestToDto(startingSession));
        return ResponseEntity.status(HttpStatus.CREATED).body(ruleMapper.dtoToResponse(dto));
    }
}
