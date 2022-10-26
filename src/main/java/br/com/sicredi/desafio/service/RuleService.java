package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.service.dto.RuleSessionDto;

public interface RuleService {

    RuleDto create(RuleDto rule);

    RuleDto get(Long id);

    RuleDto startVotingSession(Long id, RuleSessionDto session);

    void countRuleResult(RuleDto ruleDto);

    void endVotingSession(RuleSessionDto sessionDto);
}
