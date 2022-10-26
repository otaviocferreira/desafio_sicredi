package br.com.sicredi.desafio.service.impl;

import br.com.sicredi.desafio.client.ValidateAssociateWebClient;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.repository.VoteRepository;
import br.com.sicredi.desafio.service.ValidationService;
import br.com.sicredi.desafio.validation.AssociateEnableToVote;
import br.com.sicredi.desafio.validation.NoDuplicatedVote;
import br.com.sicredi.desafio.validation.RuleSessionEndDateInTimeToVote;
import br.com.sicredi.desafio.validation.RuleSessionStatusOpenToVote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ValidationServiceImpl implements ValidationService {

    private final VoteRepository voteRepository;

    private final ValidateAssociateWebClient webClient;

    @Override
    public void validate(RuleDto ruleDto, AssociateDto associateDto) {
        boolean statusOpenToVote = false;
        boolean endDateInTimeToVote = false;
        boolean noDuplicatedVote = NoDuplicatedVote.test(ruleDto, associateDto, voteRepository);
        boolean associateEnableToVote = AssociateEnableToVote.test(associateDto, webClient);

        if (Objects.nonNull(ruleDto.getSession())) {
            statusOpenToVote = RuleSessionStatusOpenToVote.test(ruleDto.getSession());
            endDateInTimeToVote = RuleSessionEndDateInTimeToVote.test(ruleDto.getSession());
        }

        if (statusOpenToVote && endDateInTimeToVote && noDuplicatedVote && associateEnableToVote) {
            log.info("The associate {} can vote on '{}' rule.", associateDto.getDocument(), ruleDto.getName());
        } else {
            throw new RuntimeException("This vote is invalid.");
        }
    }
}
