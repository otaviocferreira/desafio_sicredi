package br.com.sicredi.desafio.mother;

import br.com.sicredi.desafio.controller.request.VoteRequest;
import br.com.sicredi.desafio.enums.VoteOption;
import br.com.sicredi.desafio.repository.entity.Vote;
import br.com.sicredi.desafio.repository.entity.VoteKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static br.com.sicredi.desafio.enums.VoteOption.YES;
import static br.com.sicredi.desafio.mother.AssociateMother.getAssociate;
import static br.com.sicredi.desafio.mother.RuleMother.getRule;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteMother {

    public static Vote getVote() {
        Vote vote = new Vote();

        vote.setId(getVoteKey());
        vote.setAssociate(getAssociate());
        vote.setRule(getRule());
        vote.setOption(YES);

        return vote;
    }

    public static VoteRequest getVoteRequest() {
        return new VoteRequest(1L, 1L, YES);
    }

    public static VoteKey getVoteKey() {
        VoteKey voteKey = new VoteKey();

        voteKey.setAssociateId(1L);
        voteKey.setRuleId(1L);

        return voteKey;
    }
}
