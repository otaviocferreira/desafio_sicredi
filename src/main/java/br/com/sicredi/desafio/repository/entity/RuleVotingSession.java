package br.com.sicredi.desafio.repository.entity;

import br.com.sicredi.desafio.repository.common.enums.RuleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class RuleVotingSession implements Serializable {

    @Id
    @Column(name = "rule_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "rule_id")
    private Rule rule;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private RuleStatus status;
}
