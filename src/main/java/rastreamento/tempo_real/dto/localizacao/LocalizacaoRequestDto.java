package rastreamento.tempo_real.dto.localizacao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LocalizacaoRequestDto(
        @NotNull(message = "A latitude não pode ser nula")
        BigDecimal latitude,

        @NotNull(message = "A longitude não pode ser nula")
        BigDecimal longitude,

        @NotNull(message = "O id da entrega não pode ser nulo")
        Long entregaId) {}