package rastreamento.tempo_real.dto.entrega;

import rastreamento.tempo_real.enums.StatusEntrega;

public record EntregaResumeDto(
        Long id,
        StatusEntrega statusEntrega) {}