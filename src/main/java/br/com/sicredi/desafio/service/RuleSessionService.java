package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.service.dto.RuleSessionDto;

import java.util.Set;

public interface RuleSessionService {
    RuleSessionDto startVotingSession(RuleSessionDto session);

    void endVotingSession(RuleSessionDto sessionDto);

    Set<RuleSessionDto> closeExpiredSessions();
}
