package br.com.sicredi.desafio.validation;

import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.repository.VoteRepository;
import br.com.sicredi.desafio.repository.entity.Vote;
import br.com.sicredi.desafio.repository.entity.VoteKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class NoDuplicatedVote {

    public static boolean test(RuleDto ruleDto, AssociateDto associateDto, VoteRepository repository) {
        VoteKey voteKey = VoteKey.builder()
                .ruleId(ruleDto.getId())
                .associateId(associateDto.getId())
                .build();

        Optional<Vote> voteOptional = repository.findById(voteKey);

        return voteOptional.isEmpty();
    }
}
