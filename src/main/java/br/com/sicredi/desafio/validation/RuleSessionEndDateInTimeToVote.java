package br.com.sicredi.desafio.validation;

import br.com.sicredi.desafio.service.dto.RuleSessionDto;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class RuleSessionEndDateInTimeToVote {

    public static boolean test(RuleSessionDto ruleSessionDto) {
        log.info("Validating if the session is not expired.");

        return ruleSessionDto.getEndDate().compareTo(LocalDateTime.now()) >= 0;
    }
}
