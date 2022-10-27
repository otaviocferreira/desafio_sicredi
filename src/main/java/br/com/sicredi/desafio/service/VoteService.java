package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.enums.VoteOption;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.service.dto.VoteDto;

import java.util.Collection;

public interface VoteService {

    void create(VoteDto vote, RuleDto rule, AssociateDto associate);

    VoteOption getResult(Collection<VoteDto> votes);
}
