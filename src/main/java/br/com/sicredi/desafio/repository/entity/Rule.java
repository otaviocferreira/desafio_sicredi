package br.com.sicredi.desafio.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
public class Rule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "rule", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RuleVotingSession session;

    @OneToMany(mappedBy = "rule")
    private Set<Vote> votes;
}