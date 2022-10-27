package br.com.sicredi.desafio.mother;

import br.com.sicredi.desafio.controller.request.RuleRequest;
import br.com.sicredi.desafio.controller.request.StartingSessionRequest;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static br.com.sicredi.desafio.enums.RuleSessionStatus.OPEN;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RuleMother {

    public static Rule getRule() {
        Rule rule = new Rule();

        rule.setId(1L);
        rule.setName("Rule Test");

        return rule;
    }

    public static RuleRequest getRuleRequest() {
        return new RuleRequest("Rule Test");
    }

    public static RuleSession getRuleVotingSession() {
        RuleSession ruleSession = new RuleSession();

        ruleSession.setId(1L);
        ruleSession.setStartDate(LocalDateTime.now());
        ruleSession.setEndDate(LocalDateTime.now().plusMinutes(1));
        ruleSession.setStatus(OPEN);

        return ruleSession;
    }

    public static StartingSessionRequest getStartingSessionRequest() {
        return new StartingSessionRequest(LocalDateTime.now(), "PT10M");
    }

    public static Rule getRuleWithSession() {
        Rule rule = new Rule();

        rule.setId(1L);
        rule.setName("Rule Test");
        rule.setSession(getRuleVotingSession());

        return rule;
    }
}
