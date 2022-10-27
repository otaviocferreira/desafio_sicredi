package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.enums.RuleSessionStatus;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface RuleSessionRepository extends CrudRepository<RuleSession, Long> {

    Collection<RuleSession> findByStatusEqualsAndEndDateLessThan(RuleSessionStatus status, LocalDateTime now);
}
