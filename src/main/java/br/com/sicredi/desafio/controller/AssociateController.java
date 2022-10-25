package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.repository.AssociateRepository;
import br.com.sicredi.desafio.repository.entity.Associate;
import br.com.sicredi.desafio.repository.entity.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/associates")
public class AssociateController {

    private final AssociateRepository associateRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Associate> getAssociate(@PathVariable Long id) {
        return ResponseEntity.of(associateRepository.findById(id));
    }
}
