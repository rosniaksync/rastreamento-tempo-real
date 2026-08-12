package rastreamento.tempo_real.dto.entrega;

import rastreamento.tempo_real.dto.entregador.EntregadorResumeDto;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoResumeDto;
import rastreamento.tempo_real.enums.StatusEntrega;

import java.time.LocalDateTime;
import java.util.List;

public record EntregaResponseDto(
        Long id,
        LocalDateTime criadaEm,
        LocalDateTime finalizadaEm,
        StatusEntrega statusEntrega,
        EntregadorResumeDto entregador,
        List<LocalizacaoResumeDto> localizacoes) {}