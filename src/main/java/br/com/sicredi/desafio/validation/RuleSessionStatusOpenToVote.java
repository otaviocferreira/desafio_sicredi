package br.com.sicredi.desafio.validation;

import br.com.sicredi.desafio.service.dto.RuleSessionDto;
import lombok.extern.slf4j.Slf4j;

import static br.com.sicredi.desafio.enums.RuleSessionStatus.OPEN;

@Slf4j
public class RuleSessionStatusOpenToVote {

    public static boolean test(RuleSessionDto ruleSessionDto) {
        log.info("Validating if the session is {}.", OPEN);
        return ruleSessionDto.getStatus().equals(OPEN);
    }
}
