package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.dto.RuleDto;
import br.com.sicredi.desafio.dto.RuleSessionDto;

public interface RuleService {

    RuleDto create(RuleDto rule);

    RuleDto get(Long id);

    RuleDto startVotingSession(Long id, RuleSessionDto session);
}
