package br.com.sicredi.desafio.repository;

import br.com.sicredi.desafio.repository.entity.Vote;
import br.com.sicredi.desafio.repository.entity.VoteKey;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, VoteKey> {
}
