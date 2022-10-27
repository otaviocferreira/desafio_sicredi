package br.com.sicredi.desafio.mapper;

import br.com.sicredi.desafio.controller.request.AssociateRequest;
import br.com.sicredi.desafio.controller.response.AssociateResponse;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.repository.entity.Associate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AssociateMapper {

    AssociateDto requestToDto(AssociateRequest request);

    @Mapping(ignore = true, target = "votes")
    Associate dtoToEntity(AssociateDto dto);

    AssociateDto entityToDto(Associate entity);

    AssociateResponse dtoToResponse(AssociateDto dto);
}