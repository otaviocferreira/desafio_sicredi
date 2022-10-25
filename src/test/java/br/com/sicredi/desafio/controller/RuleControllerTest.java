package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.repository.RuleRepository;
import br.com.sicredi.desafio.repository.RuleSessionRepository;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static br.com.sicredi.desafio.mother.RuleMother.getRule;
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

    @MockBean
    private RuleRepository ruleRepository;

    @MockBean
    private RuleSessionRepository ruleSessionRepository;

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
    public void startRuleVotingSession() throws Exception {
        Rule rule = getRule();
        RuleSession ruleSession = getRuleVotingSession();

        when(ruleRepository.findById(1L)).thenReturn(Optional.of(rule));
        when(ruleSessionRepository.save(any(RuleSession.class))).thenReturn(ruleSession);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/rules/1/start-session")
                        .content(mapper.writeValueAsBytes(getStartingSessionRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        RuleSession ruleResponse = mapper.readValue(response.getContentAsByteArray(), RuleSession.class);

        assertThat(ruleResponse).isNotNull();
        assertThat(ruleResponse.getRule()).isNotNull();
        assertThat(ruleResponse.getRule().getId()).isEqualTo(ruleSession.getRule().getId());
    }
}
