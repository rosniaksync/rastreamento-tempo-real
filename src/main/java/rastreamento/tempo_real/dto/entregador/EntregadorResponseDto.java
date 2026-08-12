package rastreamento.tempo_real.dto.entregador;

import rastreamento.tempo_real.dto.entrega.EntregaResumeDto;
import rastreamento.tempo_real.enums.StatusEntregador;

import java.util.List;

public record EntregadorResponseDto(
        Long id,
        String nome,
        String telefone,
        StatusEntregador statusEntregador,
        List<EntregaResumeDto> entregas) {}