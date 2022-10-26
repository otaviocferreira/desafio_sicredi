package br.com.sicredi.desafio.mapper;

import br.com.sicredi.desafio.controller.request.RuleRequest;
import br.com.sicredi.desafio.controller.response.RuleResponse;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import br.com.sicredi.desafio.service.dto.RuleDto;
import br.com.sicredi.desafio.service.dto.RuleSessionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RuleMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "session")
    RuleDto requestToDto(RuleRequest request);

    @Mapping(ignore = true, target = "votes")
    Rule dtoToEntity(RuleDto dto);

    @Mapping(target = "session", expression = "java(entityToDto(entity.getSession()))")
    RuleDto entityToDto(Rule entity);

    RuleResponse dtoToResponse(RuleDto dto);

    @Mapping(ignore = true, target = "duration")
    RuleSessionDto entityToDto(RuleSession entity);
}