package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.controller.request.StartingSessionRequest;
import br.com.sicredi.desafio.repository.RuleRepository;
import br.com.sicredi.desafio.repository.RuleVotingSessionRepository;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleVotingSession;
import lombok.RequiredArgsConstructor;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rules")
public class RuleController {

    private final RuleRepository ruleRepository;

    private final RuleVotingSessionRepository ruleVotingSessionRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Rule> getRule(@PathVariable Long id) {
        return ResponseEntity.of(ruleRepository.findById(id));
    }

    @PostMapping(path = "/{id}/start-session", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> getRule(@PathVariable Long id, @RequestBody StartingSessionRequest startingSession) {
        RuleVotingSession ruleVotingSession = new RuleVotingSession();

        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found!"));

        LocalDateTime startDate = Objects.nonNull(startingSession.startDate()) ? startingSession.startDate() : LocalDateTime.now();
        Duration durationSent = Objects.nonNull(startingSession.duration()) ? Duration.parse(startingSession.duration()) : Duration.parse("PT1M");

        ruleVotingSession.setRule(rule);
        ruleVotingSession.setStartDate(startDate);
        ruleVotingSession.setEndDate(startingSession.startDate().plus(durationSent));

        RuleVotingSession savedRuleVotingSession = ruleVotingSessionRepository.save(ruleVotingSession);

        rule.setSession(savedRuleVotingSession);

        ruleRepository.save(rule);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
