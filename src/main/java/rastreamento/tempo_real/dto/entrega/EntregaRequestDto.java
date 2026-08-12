package rastreamento.tempo_real.dto.entrega;

import jakarta.validation.constraints.NotNull;

public record EntregaRequestDto(
        @NotNull(message = "O id do entregador não pode ser nulo")
        Long entregadorId) {}