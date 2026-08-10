package rastreamento.tempo_real.dto;

import rastreamento.tempo_real.enums.StatusEntregador;

public record EntregadorRequestDto(String nome,
                                   String telefone) {
}
