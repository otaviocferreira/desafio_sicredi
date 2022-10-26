package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.repository.entity.RuleSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleSessionRepository extends CrudRepository<RuleSession, Long> {
}
