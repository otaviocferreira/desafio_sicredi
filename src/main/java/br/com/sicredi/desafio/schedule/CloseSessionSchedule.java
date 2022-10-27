package br.com.sicredi.desafio.schedule;

import br.com.sicredi.desafio.service.RuleService;
import br.com.sicredi.desafio.service.dto.RuleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CloseSessionSchedule {

    private final RuleService ruleService;

    @Scheduled(cron = "${close_session_schedule}")
    public void closeSession() {
        MDC.put("correlation_id", UUID.randomUUID().toString());
        log.info("Starting scheduled close for expired sessions...");
        Set<RuleDto> ruleDtos = ruleService.closeExpiredSessions();

        // AQUI DEVERIA SER A CHAMADA PARA INCLUSAO NO KAFKA
        // MAS ESTOU TENDO PROBLEMAS PARA SUBIR UM CONTEINER DELE NA MINHA MAQUINA
        // AI VAI COMPLICAR PARA IMPLEMENTAR
        // DESCULPAS !!!

        // VOU IMPRIMIR UM RESULTADO PARA EXEMPLIFICAR O QUE EU COLOCARIA NA FILA

        ruleDtos.forEach(rule -> log.info("Rule '{}' has finished with result {} !!!", rule.getName(), rule.getResult()));
    }

}
