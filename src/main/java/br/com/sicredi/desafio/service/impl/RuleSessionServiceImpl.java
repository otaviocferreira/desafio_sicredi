package br.com.sicredi.desafio.service.impl;

import br.com.sicredi.desafio.mapper.RuleSessionMapper;
import br.com.sicredi.desafio.repository.RuleSessionRepository;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import br.com.sicredi.desafio.service.RuleSessionService;
import br.com.sicredi.desafio.service.dto.RuleSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static br.com.sicredi.desafio.enums.RuleSessionStatus.CLOSED;
import static br.com.sicredi.desafio.enums.RuleSessionStatus.OPEN;
import static java.util.stream.Collectors.toSet;

@Slf4j
@RequiredArgsConstructor
@Service
public class RuleSessionServiceImpl implements RuleSessionService {

    private final RuleSessionRepository ruleSessionRepository;

    private final RuleSessionMapper mapper;

    @Override
    public RuleSessionDto startVotingSession(RuleSessionDto session) {
        log.info("Starting session.");

        RuleSession ruleSession = new RuleSession();

        LocalDateTime startDate = Objects.nonNull(session.getStartDate()) ? session.getStartDate() : LocalDateTime.now();
        Duration durationSent = Objects.nonNull(session.getDuration()) ? Duration.parse(session.getDuration()) : Duration.parse("PT1M");

        log.info("The session will start at {} with duration of {} seconds.", startDate.toString(), durationSent.toSeconds());

        ruleSession.setStatus(OPEN);
        ruleSession.setStartDate(startDate);
        ruleSession.setEndDate(startDate.plus(durationSent));

        RuleSession savedRuleSession = ruleSessionRepository.save(ruleSession);

        return mapper.entityToDto(savedRuleSession);
    }

    @Override
    public void endVotingSession(RuleSessionDto sessionDto) {
        log.info("Ending voting session {}.", sessionDto);
        Optional<RuleSession> session = ruleSessionRepository.findById(sessionDto.getId());
        session.ifPresent(this::closeExpiredSession);
    }

    @Override
    public Set<RuleSessionDto> closeExpiredSessions() {
        log.info("Closing expired sessions.");

        Collection<RuleSession> sessions = ruleSessionRepository.findByStatusEqualsAndEndDateLessThan(OPEN, LocalDateTime.now());

        sessions.forEach(this::closeExpiredSession);

        return sessions.stream()
                .map(mapper::entityToDto)
                .collect(toSet());
    }

    private void closeExpiredSession(RuleSession sessionDB) {
        log.info("Closing {}.", sessionDB);
        sessionDB.setStatus(CLOSED);
        ruleSessionRepository.save(sessionDB);
    }
}
