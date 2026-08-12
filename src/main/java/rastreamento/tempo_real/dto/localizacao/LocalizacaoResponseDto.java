package rastreamento.tempo_real.dto.localizacao;

import rastreamento.tempo_real.dto.entrega.EntregaResumeDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LocalizacaoResponseDto(
        Long id,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalDateTime registradaEm,
        EntregaResumeDto entrega) {}