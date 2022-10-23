package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleVotingSession;
import org.springframework.data.repository.CrudRepository;

public interface RuleVotingSessionRepository extends CrudRepository<RuleVotingSession, Rule> {
}
