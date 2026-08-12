package rastreamento.tempo_real.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoRequestDto;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoResponseDto;
import rastreamento.tempo_real.service.LocalizacaoService;

@Controller
@RequiredArgsConstructor
public class LocalizacaoWebSocketController {

    private final LocalizacaoService service;

    @MessageMapping("/entregas/{entregaId}/localizacao")
    @SendTo("/topic/entregas/{entregaId}")
    public LocalizacaoResponseDto receberLocalizacao(@DestinationVariable Long entregaId, LocalizacaoRequestDto dto) {
        return service.cadastrar(dto);
    }
}