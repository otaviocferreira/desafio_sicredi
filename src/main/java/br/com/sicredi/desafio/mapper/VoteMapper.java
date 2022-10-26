package br.com.sicredi.desafio.mapper;

import br.com.sicredi.desafio.controller.request.VoteRequest;
import br.com.sicredi.desafio.service.dto.VoteDto;
import br.com.sicredi.desafio.repository.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VoteMapper {

    @Mapping(target = "idRule", source = "idRule")
    @Mapping(target = "idAssociate", source = "idAssociate")
    VoteDto requestToDto(VoteRequest request);

    @Mapping(target = "id.ruleId", source = "idRule")
    @Mapping(target = "id.associateId", source = "idAssociate")
    Vote dtoToEntity(VoteDto dto);

    @Mapping(target = "idRule", source = "id.ruleId")
    @Mapping(target = "idAssociate", source = "id.associateId")
    VoteDto entityToDto(Vote entity);
}