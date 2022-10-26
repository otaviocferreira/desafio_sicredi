package br.com.sicredi.desafio.validation;

import br.com.sicredi.desafio.client.ValidateAssociateWebClient;
import br.com.sicredi.desafio.client.response.AssociateStatusResponse;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static br.com.sicredi.desafio.client.enums.AssociateStatus.ABLE_TO_VOTE;

@Slf4j
public class AssociateEnableToVote {

    public static boolean test(AssociateDto associateDto, ValidateAssociateWebClient webClient) {
        Optional<AssociateStatusResponse> associateStatusToVote = webClient.getAssociateStatusToVote(associateDto);

        return associateStatusToVote.map(associateStatusResponse ->
                associateStatusResponse.getStatus().equals(ABLE_TO_VOTE)).orElse(false);
    }
}
