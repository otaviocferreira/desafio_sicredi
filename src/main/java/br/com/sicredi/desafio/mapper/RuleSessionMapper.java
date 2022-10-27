package br.com.sicredi.desafio.mapper;

import br.com.sicredi.desafio.controller.request.StartingSessionRequest;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import br.com.sicredi.desafio.service.dto.RuleSessionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RuleSessionMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "endDate")
    @Mapping(ignore = true, target = "status")
    RuleSessionDto requestToDto(StartingSessionRequest request);

    @Mapping(ignore = true, target = "duration")
    RuleSessionDto entityToDto(RuleSession entity);

    RuleSession dtoToEntity(RuleSessionDto dto);
}