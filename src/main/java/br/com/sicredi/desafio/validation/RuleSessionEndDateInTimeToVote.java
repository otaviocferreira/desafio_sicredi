package br.com.sicredi.desafio.validation;

import br.com.sicredi.desafio.service.dto.RuleSessionDto;

import java.time.LocalDateTime;

public class RuleSessionEndDateInTimeToVote {

    public static boolean test(RuleSessionDto ruleSessionDto) {
        return ruleSessionDto.getEndDate().compareTo(LocalDateTime.now()) <= 0;
    }
}
