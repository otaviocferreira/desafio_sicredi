package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.controller.request.VoteRequest;
import br.com.sicredi.desafio.mapper.VoteMapper;
import br.com.sicredi.desafio.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

    private final VoteService voteService;

    private final VoteMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody VoteRequest request) {
        voteService.create(mapper.requestToDto(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
