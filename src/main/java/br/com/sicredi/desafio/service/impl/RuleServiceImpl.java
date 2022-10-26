package br.com.sicredi.desafio.service.impl;

import br.com.sicredi.desafio.enums.VoteOption;
import br.com.sicredi.desafio.exception.NotFoundException;
import br.com.sicredi.desafio.mapper.RuleMapper;
import br.com.sicredi.desafio.mapper.RuleSessionMapper;
import br.com.sicredi.desafio.mapper.VoteMapper;
import br.com.sicredi.desafio.repository.RuleRepository;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import br.com.sicredi.desafio.service.RuleService;
import br.com.sicredi.desafio.service.RuleSessionService;
import br.com.sicredi.desafio.service.VoteService;
import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.service.dto.RuleSessionDto;
import br.com.sicredi.desafio.service.dto.VoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Slf4j
@RequiredArgsConstructor
@Service
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    private final RuleSessionService ruleSessionService;

    private final VoteService voteService;

    private final RuleMapper ruleMapper;

    private final RuleSessionMapper ruleSessionMapper;

    private final VoteMapper voteMapper;

    @Override
    public RuleDto create(RuleDto rule) {
        log.info("Creating new {}.", rule);
        return ruleMapper.entityToDto(ruleRepository.save(ruleMapper.dtoToEntity(rule)));
    }

    @Override
    public RuleDto get(Long id) {
        log.info("Getting rule with ID {}.", id);

        Rule ruleDB = ruleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rule not found!"));

        return ruleMapper.entityToDto(ruleDB);
    }

    @Override
    public RuleDto startVotingSession(Long id, RuleSessionDto session) {
        log.info("Starting voting session to rule with ID {}.", id);

        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rule not found!"));

        RuleSessionDto savedRuleSession = ruleSessionService.startVotingSession(session);

        rule.setSession(ruleSessionMapper.dtoToEntity(savedRuleSession));

        return ruleMapper.entityToDto(ruleRepository.save(rule));
    }

    private Rule countRuleResult(Rule rule) {
        log.info("Getting voting results to rule {}.", rule);
        getResult(rule);
        return ruleRepository.save(rule);
    }

    @Transactional
    @Override
    public Set<RuleDto> closeExpiredSessions() {
        log.info("Closing expired sessions.");

        Set<RuleSessionDto> sessionsDto = ruleSessionService.closeExpiredSessions();

        Set<RuleSession> sessions = sessionsDto.stream()
                .map(ruleSessionMapper::dtoToEntity)
                .collect(toSet());

        Collection<Rule> closedRules = ruleRepository.findBySessionIn(sessions);

        return closedRules
                .stream()
                .map(this::countRuleResult)
                .map(ruleMapper::entityToDto)
                .collect(toSet());
    }

    private void getResult(Rule ruleDB) {
        log.info("Calculating the results to {} ...", ruleDB);

        Set<VoteDto> votes = ruleDB.getVotes().stream()
                .map(voteMapper::entityToDto)
                .collect(toSet());

        VoteOption result = voteService.getResult(votes);

        ruleDB.setResult(result);

        log.info("The result is {} ...", ruleDB.getResult());
    }
}
