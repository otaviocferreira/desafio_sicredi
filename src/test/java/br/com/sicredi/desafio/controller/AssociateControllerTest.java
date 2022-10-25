package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.repository.AssociateRepository;
import br.com.sicredi.desafio.repository.entity.Associate;
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

import static br.com.sicredi.desafio.mother.AssociateMother.getAssociate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AssociateController.class)
public class AssociateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AssociateRepository associateRepository;

    @Test
    public void getSimpleAssociate() throws Exception {
        Associate associate = getAssociate();

        when(associateRepository.findById(1L)).thenReturn(Optional.of(associate));

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/api/v1/associates/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        Associate associateResponse = mapper.readValue(response.getContentAsByteArray(), Associate.class);

        assertThat(associateResponse).isNotNull();
        assertThat(associateResponse.getId()).isEqualTo(associate.getId());
        assertThat(associateResponse.getName()).isEqualTo(associate.getName());
    }
}
