package br.com.sicredi.desafio.mother;

import br.com.sicredi.desafio.client.enums.AssociateStatus;
import br.com.sicredi.desafio.client.response.AssociateStatusResponse;
import br.com.sicredi.desafio.controller.request.AssociateRequest;
import br.com.sicredi.desafio.controller.response.AssociateResponse;
import br.com.sicredi.desafio.repository.entity.Associate;
import br.com.sicredi.desafio.service.dto.AssociateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static br.com.sicredi.desafio.client.enums.AssociateStatus.ABLE_TO_VOTE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociateMother {

    public static Associate getAssociate() {
        Associate associate = new Associate();

        associate.setId(1L);
        associate.setName("John Doe");
        associate.setDocument("12345678909");

        return associate;
    }

    public static AssociateDto getAssociateDto() {
        AssociateDto associate = new AssociateDto();

        associate.setId(1L);
        associate.setName("John Doe");

        return associate;
    }

    public static AssociateResponse getAssociateResponse() {
        AssociateResponse associate = new AssociateResponse();

        associate.setId(1L);
        associate.setName("John Doe");

        return associate;
    }

    public static AssociateRequest getAssociateRequest() {
        return new AssociateRequest("12345678909", "John Doe");
    }

    public static AssociateStatusResponse getAssociateStatusResponse() {
        AssociateStatusResponse response = new AssociateStatusResponse();

        response.setStatus(ABLE_TO_VOTE);

        return response;
    }
}
