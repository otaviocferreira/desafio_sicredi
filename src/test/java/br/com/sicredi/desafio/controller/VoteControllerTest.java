package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.client.ValidateAssociateWebClient;
import br.com.sicredi.desafio.client.response.AssociateStatusResponse;
import br.com.sicredi.desafio.controller.request.AssociateRequest;
import br.com.sicredi.desafio.controller.request.VoteRequest;
import br.com.sicredi.desafio.mapper.AssociateMapperImpl;
import br.com.sicredi.desafio.mapper.RuleMapperImpl;
import br.com.sicredi.desafio.mapper.RuleSessionMapperImpl;
import br.com.sicredi.desafio.mapper.VoteMapperImpl;
import br.com.sicredi.desafio.repository.AssociateRepository;
import br.com.sicredi.desafio.repository.RuleRepository;
import br.com.sicredi.desafio.repository.RuleSessionRepository;
import br.com.sicredi.desafio.repository.VoteRepository;
import br.com.sicredi.desafio.repository.entity.Associate;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.Vote;
import br.com.sicredi.desafio.repository.entity.VoteKey;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.impl.AssociateServiceImpl;
import br.com.sicredi.desafio.service.impl.RuleServiceImpl;
import br.com.sicredi.desafio.service.impl.RuleSessionServiceImpl;
import br.com.sicredi.desafio.service.impl.ValidationServiceImpl;
import br.com.sicredi.desafio.service.impl.VoteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static br.com.sicredi.desafio.mother.AssociateMother.getAssociate;
import static br.com.sicredi.desafio.mother.AssociateMother.getAssociateRequest;
import static br.com.sicredi.desafio.mother.AssociateMother.getAssociateStatusResponse;
import static br.com.sicredi.desafio.mother.RuleMother.getRule;
import static br.com.sicredi.desafio.mother.RuleMother.getRuleWithSession;
import static br.com.sicredi.desafio.mother.VoteMother.getVote;
import static br.com.sicredi.desafio.mother.VoteMother.getVoteRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(VoteController.class)
public class VoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private VoteServiceImpl voteService;

    @SpyBean
    private RuleServiceImpl ruleService;

    @SpyBean
    private AssociateServiceImpl associateService;

    @SpyBean
    private VoteMapperImpl mapper;

    @MockBean
    private VoteRepository voteRepository;

    @SpyBean
    private RuleMapperImpl ruleMapper;

    @SpyBean
    private AssociateMapperImpl associateMapper;

    @SpyBean
    private ValidationServiceImpl validationService;

    @MockBean
    private ValidateAssociateWebClient webClient;

    @MockBean
    private RuleRepository ruleRepository;

    @MockBean
    private RuleSessionRepository ruleSessionRepository;

    @SpyBean
    private RuleSessionServiceImpl ruleSessionService;

    @SpyBean
    private RuleSessionMapperImpl ruleSessionMapper;

    @MockBean
    private AssociateRepository associateRepository;

    @Test
    public void createVote() throws Exception {
        Vote vote = getVote();
        VoteRequest voteRequest = getVoteRequest();
        Rule rule = getRuleWithSession();
        Associate associate = getAssociate();
        AssociateStatusResponse associateStatusResponse = getAssociateStatusResponse();

        when(associateRepository.findById(1L)).thenReturn(Optional.of(associate));
        when(ruleRepository.findById(1L)).thenReturn(Optional.of(rule));
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);
        when(voteRepository.findById(any(VoteKey.class))).thenReturn(Optional.empty());
        when(webClient.getAssociateStatusToVote(any(AssociateDto.class))).thenReturn(Optional.of(associateStatusResponse));

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/votes")
                        .content(objectMapper.writeValueAsBytes(voteRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }
}
