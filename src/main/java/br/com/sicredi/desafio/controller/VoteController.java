package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.controller.request.VoteRequest;
import br.com.sicredi.desafio.mapper.VoteMapper;
import br.com.sicredi.desafio.service.AssociateService;
import br.com.sicredi.desafio.service.RuleService;
import br.com.sicredi.desafio.service.VoteService;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.dto.RuleDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

    private final VoteService voteService;

    private final RuleService ruleService;

    private final AssociateService associateService;

    private final VoteMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody VoteRequest request) {
        MDC.put("correlation_id", UUID.randomUUID().toString());

        RuleDto rule = ruleService.get(request.idRule());
        AssociateDto associate = associateService.get(request.idAssociate());

        voteService.create(mapper.requestToDto(request), rule, associate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
