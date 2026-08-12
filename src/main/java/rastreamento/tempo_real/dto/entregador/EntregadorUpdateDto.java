package rastreamento.tempo_real.dto.entregador;

import rastreamento.tempo_real.enums.StatusEntregador;

public record EntregadorUpdateDto(
        String nome,
        String telefone,
        StatusEntregador statusEntregador) {}