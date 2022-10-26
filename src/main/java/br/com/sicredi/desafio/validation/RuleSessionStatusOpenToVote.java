package br.com.sicredi.desafio.validation;

import br.com.sicredi.desafio.service.dto.RuleSessionDto;

import static br.com.sicredi.desafio.enums.RuleSessionStatus.OPEN;

public class RuleSessionStatusOpenToVote {

    public static boolean test(RuleSessionDto ruleSessionDto) {
        return ruleSessionDto.getStatus().equals(OPEN);
    }
}
