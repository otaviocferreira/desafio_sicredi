package br.com.sicredi.desafio.repository.entity;

import br.com.sicredi.desafio.repository.common.enums.VoteOption;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
public class Vote implements Serializable {

    @EmbeddedId
    VoteKey id;

    @ManyToOne
    @MapsId("ruleId")
    @JoinColumn(name = "rule_id")
    private Rule rule;

    @ManyToOne
    @MapsId("associateId")
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @Enumerated(EnumType.STRING)
    private VoteOption option;
}
