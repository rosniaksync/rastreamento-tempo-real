package rastreamento.tempo_real.dto;

import rastreamento.tempo_real.enums.StatusEntregador;

import java.util.List;

public record EntregadorResponseDto(Long id,
                                    String nome,
                                    String telefone,
                                    StatusEntregador statusEntregador,
                                    List<EntregaResumoDto> entregas) {
}