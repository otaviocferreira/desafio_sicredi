package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.service.dto.RuleSessionDto;

import java.util.Set;

public interface RuleService {

    RuleDto create(RuleDto rule);

    RuleDto get(Long id);

    RuleDto startVotingSession(Long id, RuleSessionDto session);

    Set<RuleDto> closeExpiredSessions();
}
