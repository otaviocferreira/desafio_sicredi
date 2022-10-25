package br.com.sicredi.desafio.mother;

import br.com.sicredi.desafio.repository.entity.Associate;
import br.com.sicredi.desafio.repository.entity.Rule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociateMother {

    public static Associate getAssociate() {
        Associate associate = new Associate();

        associate.setId(1L);
        associate.setName("John Doe");

        return associate;
    }
}
