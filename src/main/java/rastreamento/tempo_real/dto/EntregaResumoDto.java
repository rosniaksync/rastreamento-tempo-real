package rastreamento.tempo_real.dto;

import rastreamento.tempo_real.enums.StatusEntrega;

public record EntregaResumoDto(Long id,
                               StatusEntrega statusEntrega) {
}
