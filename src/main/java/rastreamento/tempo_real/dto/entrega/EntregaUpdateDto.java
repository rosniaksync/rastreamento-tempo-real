package rastreamento.tempo_real.dto.entrega;

import rastreamento.tempo_real.enums.StatusEntrega;
import java.time.LocalDateTime;

public record EntregaUpdateDto(
        LocalDateTime finalizadaEm,
        StatusEntrega statusEntrega,
        Long entregadorId) {}