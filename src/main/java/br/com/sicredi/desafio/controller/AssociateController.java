package br.com.sicredi.desafio.controller;

import br.com.sicredi.desafio.controller.request.AssociateRequest;
import br.com.sicredi.desafio.controller.response.AssociateResponse;
import br.com.sicredi.desafio.dto.AssociateDto;
import br.com.sicredi.desafio.mapper.AssociateMapper;
import br.com.sicredi.desafio.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/associates")
public class AssociateController {

    private final AssociateService associateService;

    private final AssociateMapper mapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AssociateResponse> getAssociate(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.dtoToResponse(associateService.get(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AssociateResponse> createAssociate(@RequestBody AssociateRequest request) {
        AssociateDto dto = associateService.create(mapper.requestToDto(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.dtoToResponse(dto));
    }
}
