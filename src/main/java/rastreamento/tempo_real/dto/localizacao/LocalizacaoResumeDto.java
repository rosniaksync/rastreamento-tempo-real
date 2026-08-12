package rastreamento.tempo_real.dto.localizacao;

import java.time.LocalDateTime;

public record LocalizacaoResumeDto(
        Long id,
        LocalDateTime registradaEm) {}