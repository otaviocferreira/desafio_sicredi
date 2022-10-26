package br.com.sicredi.desafio.service.impl;

import br.com.sicredi.desafio.enums.VoteOption;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.service.dto.VoteDto;
import br.com.sicredi.desafio.mapper.AssociateMapper;
import br.com.sicredi.desafio.mapper.RuleMapper;
import br.com.sicredi.desafio.mapper.VoteMapper;
import br.com.sicredi.desafio.repository.VoteRepository;
import br.com.sicredi.desafio.repository.entity.Vote;
import br.com.sicredi.desafio.service.AssociateService;
import br.com.sicredi.desafio.service.RuleService;
import br.com.sicredi.desafio.service.ValidationService;
import br.com.sicredi.desafio.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.sicredi.desafio.enums.VoteOption.DRAW;
import static br.com.sicredi.desafio.enums.VoteOption.NO;
import static br.com.sicredi.desafio.enums.VoteOption.YES;

@Slf4j
@RequiredArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final VoteMapper voteMapper;

    private final RuleMapper ruleMapper;

    private final AssociateMapper associateMapper;

    private final ValidationService validationService;

    @Override
    public void create(VoteDto vote, RuleDto rule, AssociateDto associate) {
        log.info("Creating {}.", vote);

        validationService.validate(rule, associate);

        Vote voteDB = voteMapper.dtoToEntity(vote);
        voteDB.setRule(ruleMapper.dtoToEntity(rule));
        voteDB.setAssociate(associateMapper.dtoToEntity(associate));

        voteRepository.save(voteDB);
    }

    @Override
    public VoteOption getResult(Collection<VoteDto> votes) {
        log.info("Getting results of {} votes.", votes.size());

        AtomicInteger yesVote = new AtomicInteger();
        AtomicInteger noVote = new AtomicInteger();

        votes.forEach(vote -> {
            if (vote.getOption().equals(YES)) {
                yesVote.getAndIncrement();
            } else {
                noVote.getAndIncrement();
            }
        });

        if (yesVote.get() > noVote.get()) {
            return YES;
        } else if (yesVote.get() < noVote.get()) {
            return NO;
        } else {
            return DRAW;
        }
    }
}
