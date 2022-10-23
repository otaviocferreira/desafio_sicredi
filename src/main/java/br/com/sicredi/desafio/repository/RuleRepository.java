package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.repository.entity.Rule;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, Long> {
}
