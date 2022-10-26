package br.com.sicredi.desafio.service.impl;

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
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final RuleService ruleService;

    private final AssociateService associateService;

    private final VoteMapper voteMapper;

    private final RuleMapper ruleMapper;

    private final AssociateMapper associateMapper;

    private final ValidationService validationService;

    @Override
    public void create(VoteDto vote) {

        RuleDto rule = ruleService.get(vote.getIdRule());
        AssociateDto associate = associateService.get(vote.getIdAssociate());

        validationService.validate(rule, associate);

        Vote voteDB = voteMapper.dtoToEntity(vote);
        voteDB.setRule(ruleMapper.dtoToEntity(rule));
        voteDB.setAssociate(associateMapper.dtoToEntity(associate));

        voteRepository.save(voteDB);
    }
}
