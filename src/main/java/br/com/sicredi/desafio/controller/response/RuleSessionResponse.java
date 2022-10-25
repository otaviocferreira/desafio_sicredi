package br.com.sicredi.desafio.controller.response;

import br.com.sicredi.desafio.enums.RuleSessionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleSessionResponse {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private RuleSessionStatus status;
}
