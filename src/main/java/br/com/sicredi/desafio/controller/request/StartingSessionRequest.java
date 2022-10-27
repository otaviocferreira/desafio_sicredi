package br.com.sicredi.desafio.controller.request;

import java.time.LocalDateTime;

public record StartingSessionRequest(LocalDateTime startDate, String duration) {
}
