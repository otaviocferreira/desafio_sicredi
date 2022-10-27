package br.com.sicredi.desafio.client.response;

import br.com.sicredi.desafio.client.enums.AssociateStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AssociateStatusResponse {

    private AssociateStatus status;
}
