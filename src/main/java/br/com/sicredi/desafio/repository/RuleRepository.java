package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Long> {

    Collection<Rule> findBySessionIn(Collection<RuleSession> sessions);
}
