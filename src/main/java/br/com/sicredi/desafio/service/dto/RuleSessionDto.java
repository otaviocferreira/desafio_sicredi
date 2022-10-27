package br.com.sicredi.desafio.service.dto;

import br.com.sicredi.desafio.enums.RuleSessionStatus;
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
public class RuleSessionDto {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private RuleSessionStatus status;

    private String duration;
}
