package br.com.sicredi.desafio.client;

import br.com.sicredi.desafio.client.response.AssociateStatusResponse;
import br.com.sicredi.desafio.service.dto.AssociateDto;

import java.util.Optional;

public interface ValidateAssociateWebClient {
    Optional<AssociateStatusResponse> getAssociateStatusToVote(AssociateDto associate);
}
