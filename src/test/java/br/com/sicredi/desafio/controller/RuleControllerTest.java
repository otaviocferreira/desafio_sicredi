package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.repository.RuleRepository;
import br.com.sicredi.desafio.repository.RuleVotingSessionRepository;
import br.com.sicredi.desafio.repository.entity.Rule;
import br.com.sicredi.desafio.repository.entity.RuleVotingSession;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
    private RuleVotingSessionRepository ruleVotingSessionRepository;

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
        RuleVotingSession ruleVotingSession = getRuleVotingSession();

        when(ruleRepository.findById(1L)).thenReturn(Optional.of(rule));
        when(ruleVotingSessionRepository.save(any(RuleVotingSession.class))).thenReturn(ruleVotingSession);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/rules/1/start-session")
                        .content(mapper.writeValueAsBytes(getStartingSessionRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        RuleVotingSession ruleResponse = mapper.readValue(response.getContentAsByteArray(), RuleVotingSession.class);

        assertThat(ruleResponse).isNotNull();
        assertThat(ruleResponse.getRule()).isNotNull();
        assertThat(ruleResponse.getRule().getId()).isEqualTo(ruleVotingSession.getRule().getId());
    }
}
