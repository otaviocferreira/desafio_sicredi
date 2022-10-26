package br.com.sicredi.desafio.service.dto;

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
public class RuleDto {

    private Long id;

    private String name;

    private RuleSessionDto session;

    private VoteOption result;
}
