package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.client.ValidateAssociateWebClient;
import br.com.sicredi.desafio.client.impl.ValidateAssociateWebClientImpl;
import br.com.sicredi.desafio.controller.response.RuleResponse;
import br.com.sicredi.desafio.mapper.AssociateMapperImpl;
import br.com.sicredi.desafio.mapper.RuleMapper;
import br.com.sicredi.desafio.mapper.RuleMapperImpl;
import br.com.sicredi.desafio.mapper.RuleSessionMapper;
import br.com.sicredi.desafio.mapper.RuleSessionMapperImpl;
import br.com.sicredi.desafio.mapper.VoteMapper;
import br.com.sicredi.desafio.mapper.VoteMapperImpl;
import br.com.sicredi.desafio.repository.RuleRepository;
import br.com.sicredi.desafio.repository.RuleSessionRepository;
import br.com.sicredi.desafio.repository.VoteRepository;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import br.com.sicredi.desafio.service.RuleService;
import br.com.sicredi.desafio.service.RuleSessionService;
import br.com.sicredi.desafio.service.VoteService;
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

import static br.com.sicredi.desafio.mother.RuleMother.getRule;
import static br.com.sicredi.desafio.mother.RuleMother.getRuleRequest;
import static br.com.sicredi.desafio.mother.RuleMother.getRuleVotingSession;
import static br.com.sicredi.desafio.mother.RuleMother.getStartingSessionRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RuleController.class)
public class RuleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @SpyBean
    private RuleServiceImpl ruleService;

    @SpyBean
    private RuleMapperImpl ruleMapper;

    @SpyBean
    private RuleSessionMapperImpl ruleSessionMapper;

    @MockBean
    private RuleRepository ruleRepository;

    @MockBean
    private RuleSessionRepository ruleSessionRepository;

    @MockBean
    private VoteRepository voteRepository;

    @SpyBean
    private RuleSessionServiceImpl ruleSessionService;

    @SpyBean
    private VoteServiceImpl voteService;

    @SpyBean
    private ValidationServiceImpl validationService;

    @MockBean
    private ValidateAssociateWebClient validateAssociateWebClient;

    @SpyBean
    private VoteMapperImpl voteMapper;

    @SpyBean
    private AssociateMapperImpl associateMapper;

    @Test
    public void getSimpleRule() throws Exception {
        Rule rule = getRule();

        when(ruleRepository.findById(1L)).thenReturn(Optional.of(rule));

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/api/v1/rules/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Rule ruleResponse = mapper.readValue(response.getContentAsByteArray(), Rule.class);

        assertThat(ruleResponse).isNotNull();
        assertThat(ruleResponse.getId()).isEqualTo(rule.getId());
        assertThat(ruleResponse.getName()).isEqualTo(rule.getName());
    }

    @Test
    public void createSimpleRule() throws Exception {
        Rule rule = getRule();

        when(ruleRepository.save(any(Rule.class))).thenReturn(rule);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/rules")
                        .content(mapper.writeValueAsBytes(getRuleRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        RuleResponse ruleResponse = mapper.readValue(response.getContentAsString(), RuleResponse.class);

        assertThat(ruleResponse).isNotNull();
        assertThat(ruleResponse.getId()).isEqualTo(rule.getId());
        assertThat(ruleResponse.getName()).isEqualTo(rule.getName());
        assertThat(ruleResponse.getSession()).isNull();
    }

    @Test
    public void startRuleVotingSession() throws Exception {
        Rule rule = getRule();
        RuleSession ruleSession = getRuleVotingSession();

        when(ruleRepository.findById(1L)).thenReturn(Optional.of(rule));
        when(ruleSessionRepository.save(any(RuleSession.class))).thenReturn(ruleSession);
        when(ruleRepository.save(any(Rule.class))).thenReturn(rule);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/rules/1/start-session")
                        .content(mapper.writeValueAsBytes(getStartingSessionRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        RuleResponse ruleResponse = mapper.readValue(response.getContentAsString(), RuleResponse.class);

        assertThat(ruleResponse).isNotNull();
        assertThat(ruleResponse.getId()).isEqualTo(rule.getId());
        assertThat(ruleResponse.getName()).isEqualTo(rule.getName());
        assertThat(ruleResponse.getSession()).isNotNull();
        assertThat(ruleResponse.getSession().getId()).isEqualTo(ruleSession.getId());
        assertThat(ruleResponse.getSession().getStatus()).isEqualTo(ruleSession.getStatus());
    }
}
