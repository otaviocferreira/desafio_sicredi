package br.com.sicredi.desafio.dto;

import br.com.sicredi.desafio.enums.VoteOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class VoteDto {

    private Long idRule;

    private Long idAssociate;

    private VoteOption option;
}
