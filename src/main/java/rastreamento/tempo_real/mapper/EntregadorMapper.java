package rastreamento.tempo_real.mapper;

import org.springframework.stereotype.Component;
import rastreamento.tempo_real.dto.entrega.EntregaResumeDto;
import rastreamento.tempo_real.dto.entregador.EntregadorRequestDto;
import rastreamento.tempo_real.dto.entregador.EntregadorResponseDto;
import rastreamento.tempo_real.enums.StatusEntregador;
import rastreamento.tempo_real.model.EntregadorEntity;

@Component
public class EntregadorMapper {

    public EntregadorResponseDto toResponseDto(EntregadorEntity entregador) {
        return new EntregadorResponseDto(
                entregador.getId(),
                entregador.getNome(),
                entregador.getTelefone(),
                entregador.getStatusEntregador(),
                entregador.getEntregas()
                        .stream()
                        .map(entrega -> new EntregaResumeDto(
                                entrega.getId(),
                                entrega.getStatusEntrega()
                        )).toList()
        );
    }
}