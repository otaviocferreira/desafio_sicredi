package br.com.sicredi.desafio.controller.response;

import br.com.sicredi.desafio.enums.VoteOption;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleResponse {

    private Long id;

    private String name;

    private RuleSessionResponse session;

    private VoteOption result;
}
