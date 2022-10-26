package br.com.sicredi.desafio.client.impl;

import br.com.sicredi.desafio.client.ValidateAssociateWebClient;
import br.com.sicredi.desafio.client.response.AssociateStatusResponse;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Slf4j
@Component
public class ValidateAssociateWebClientImpl implements ValidateAssociateWebClient {

    @Autowired
    @Qualifier("validateAssociate")
    private WebClient webClient;

    @Override
    public Optional<AssociateStatusResponse> getAssociateStatusToVote(AssociateDto associate) {
        AssociateStatusResponse statusResponse = null;

        try {
            log.info("Searching associate status: {}", associate);

            statusResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/".concat(associate.getDocument())).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(AssociateStatusResponse.class)
                    .block();

            assert statusResponse != null;
            log.info("Associate with document {} has status - {}", associate.getDocument(), statusResponse.getStatus());
        } catch (Exception ex) {
            log.error("There was an error trying to get the Associate status to vote with document {}.", associate.getDocument(), ex);
        }

        return Optional.ofNullable(statusResponse);
    }
}
