package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.dto.RuleDto;

public interface ValidationService {

    void validate(RuleDto ruleDto, AssociateDto associateDto);
}
