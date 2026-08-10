package rastreamento.tempo_real.mapper;

import org.springframework.stereotype.Component;
import rastreamento.tempo_real.dto.EntregaResumoDto;
import rastreamento.tempo_real.dto.EntregadorRequestDto;
import rastreamento.tempo_real.dto.EntregadorResponseDto;
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
                        .map(entrega -> new EntregaResumoDto(
                                entrega.getId(),
                                entrega.getStatusEntrega()
                        )).toList()
        );
    }
        public EntregadorEntity toEntity(EntregadorRequestDto dto) {

            return EntregadorEntity.builder()
                    .nome(dto.nome())
                    .telefone(dto.telefone())
                    .statusEntregador(StatusEntregador.DISPONIVEL)
                    .build();
    }
}