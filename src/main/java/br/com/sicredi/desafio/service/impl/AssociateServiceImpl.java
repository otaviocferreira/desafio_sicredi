package br.com.sicredi.desafio.service.impl;

import br.com.sicredi.desafio.exception.NotFoundException;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.mapper.AssociateMapper;
import br.com.sicredi.desafio.repository.AssociateRepository;
import br.com.sicredi.desafio.repository.entity.Associate;
import br.com.sicredi.desafio.service.AssociateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;

    private final AssociateMapper mapper;

    @Override
    public AssociateDto create(AssociateDto associate) {
        log.info("Creating {}.", associate);

        return mapper.entityToDto(associateRepository.save(mapper.dtoToEntity(associate)));
    }

    @Override
    public AssociateDto get(Long id) {
        log.info("Getting associate with id {}.", id);

        Associate associate = associateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Associate not found!"));

        return mapper.entityToDto(associate);
    }
}
