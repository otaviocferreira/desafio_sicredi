package br.com.sicredi.desafio.service.impl;

import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.service.dto.RuleSessionDto;
import br.com.sicredi.desafio.mapper.RuleMapper;
import br.com.sicredi.desafio.repository.RuleRepository;
import br.com.sicredi.desafio.repository.RuleSessionRepository;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import br.com.sicredi.desafio.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.sicredi.desafio.enums.RuleSessionStatus.CLOSED;
import static br.com.sicredi.desafio.enums.RuleSessionStatus.OPEN;
import static br.com.sicredi.desafio.enums.VoteOption.DRAW;
import static br.com.sicredi.desafio.enums.VoteOption.NO;
import static br.com.sicredi.desafio.enums.VoteOption.YES;

@RequiredArgsConstructor
@Service
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    private final RuleSessionRepository ruleSessionRepository;

    private final RuleMapper mapper;

    @Override
    public RuleDto create(RuleDto rule) {

        return mapper.entityToDto(ruleRepository.save(mapper.dtoToEntity(rule)));
    }

    @Override
    public RuleDto get(Long id) {
        Rule ruleDB = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found!"));

        return mapper.entityToDto(ruleDB);
    }

    @Override
    public RuleDto startVotingSession(Long id, RuleSessionDto session) {
        RuleSession ruleSession = new RuleSession();

        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found!"));

        LocalDateTime startDate = Objects.nonNull(session.getStartDate()) ? session.getStartDate() : LocalDateTime.now();
        Duration durationSent = Objects.nonNull(session.getDuration()) ? Duration.parse(session.getDuration()) : Duration.parse("PT1M");

        ruleSession.setStatus(OPEN);
        ruleSession.setStartDate(startDate);
        ruleSession.setEndDate(startDate.plus(durationSent));

        RuleSession savedRuleSession = ruleSessionRepository.save(ruleSession);

        rule.setSession(savedRuleSession);

        return mapper.entityToDto(ruleRepository.save(rule));
    }

    @Override
    public void countRuleResult(RuleDto ruleDto) {
        Optional<Rule> rule = ruleRepository.findById(ruleDto.getId());

        rule.ifPresent(ruleDB -> {
            getResult(ruleDB);
            ruleRepository.save(ruleDB);
        });
    }

    private void getResult(Rule ruleDB) {
        AtomicInteger yesVote = new AtomicInteger();
        AtomicInteger noVote = new AtomicInteger();

        ruleDB.getVotes().forEach(vote -> {
            if (vote.getOption().equals(YES)) {
                yesVote.getAndIncrement();
            } else {
                noVote.getAndIncrement();
            }
        });

        if (yesVote.get() > noVote.get()) {
            ruleDB.setResult(YES);
        } else if (yesVote.get() < noVote.get()) {
            ruleDB.setResult(NO);
        } else {
            ruleDB.setResult(DRAW);
        }
    }

    @Override
    public void endVotingSession(RuleSessionDto sessionDto) {
        Optional<RuleSession> session = ruleSessionRepository.findById(sessionDto.getId());

        session.ifPresent(sessionDB -> {
            sessionDB.setStatus(CLOSED);
            ruleSessionRepository.save(sessionDB);
        });
    }
}
