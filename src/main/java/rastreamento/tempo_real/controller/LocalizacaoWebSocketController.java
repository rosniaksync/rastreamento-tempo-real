package rastreamento.tempo_real.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoRequestDto;
import rastreamento.tempo_real.service.LocalizacaoService;

@Controller
@RequiredArgsConstructor
public class LocalizacaoWebSocketController {

    private final LocalizacaoService service;

    @MessageMapping("/entrega/{entregaId}/localizacao")
    @SendTo("/topic/entrega/{entregaId}")
    public void receberLocalizacao(@DestinationVariable Long entregaId, LocalizacaoRequestDto dto) {
        service.cadastrar(dto);
    }
}