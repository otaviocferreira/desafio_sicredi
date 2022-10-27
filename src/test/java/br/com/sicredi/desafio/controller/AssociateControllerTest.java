package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.controller.request.AssociateRequest;
import br.com.sicredi.desafio.controller.response.AssociateResponse;
import br.com.sicredi.desafio.mapper.AssociateMapper;
import br.com.sicredi.desafio.mapper.AssociateMapperImpl;
import br.com.sicredi.desafio.repository.AssociateRepository;
import br.com.sicredi.desafio.repository.entity.Associate;
import br.com.sicredi.desafio.service.AssociateService;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import br.com.sicredi.desafio.service.impl.AssociateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static br.com.sicredi.desafio.mother.AssociateMother.getAssociate;
import static br.com.sicredi.desafio.mother.AssociateMother.getAssociateDto;
import static br.com.sicredi.desafio.mother.AssociateMother.getAssociateRequest;
import static br.com.sicredi.desafio.mother.AssociateMother.getAssociateResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AssociateController.class)
public class AssociateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private AssociateServiceImpl associateService;

    @MockBean
    private AssociateRepository associateRepository;

    @SpyBean
    private AssociateMapperImpl mapper;

    @Test
    public void getSimpleAssociate() throws Exception {
        Associate associate = getAssociate();

        when(associateRepository.findById(1L)).thenReturn(Optional.of(associate));

        MockHttpServletResponse restResponse = mvc.perform(MockMvcRequestBuilders.get("/api/v1/associates/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        AssociateDto response = objectMapper.readValue(restResponse.getContentAsByteArray(), AssociateDto.class);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("John Doe");
    }

    @Test
    public void createSimpleAssociate() throws Exception {
        Associate associate = getAssociate();
        AssociateRequest associateRequest = getAssociateRequest();

        when(associateRepository.save(any(Associate.class))).thenReturn(associate);

        MockHttpServletResponse restResponse = mvc.perform(MockMvcRequestBuilders.post("/api/v1/associates")
                        .content(objectMapper.writeValueAsBytes(associateRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        AssociateDto response = objectMapper.readValue(restResponse.getContentAsByteArray(), AssociateDto.class);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo(associateRequest.name());
        assertThat(response.getDocument()).isEqualTo(associateRequest.document());
    }
}
