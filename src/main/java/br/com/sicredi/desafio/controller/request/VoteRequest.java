package br.com.sicredi.desafio.controller.request;

import br.com.sicredi.desafio.enums.VoteOption;

public record VoteRequest(Long idRule, Long idAssociate, VoteOption option) {
}
