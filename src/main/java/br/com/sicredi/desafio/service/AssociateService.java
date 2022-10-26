package br.com.sicredi.desafio.service;

import br.com.sicredi.desafio.service.dto.AssociateDto;

public interface AssociateService {

    AssociateDto create(AssociateDto associate);

    AssociateDto get(Long id);
}
