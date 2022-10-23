package br.com.sicredi.desafio.repository.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VoteKey implements Serializable {

    @Column(name = "rule_id")
    private Long ruleId;

    @Column(name = "associate_id")
    private Long associateId;
}
