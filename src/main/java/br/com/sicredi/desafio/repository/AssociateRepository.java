package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.repository.entity.Associate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends CrudRepository<Associate, Long> {
}
