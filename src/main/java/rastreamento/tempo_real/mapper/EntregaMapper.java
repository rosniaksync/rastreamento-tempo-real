package rastreamento.tempo_real.mapper;

import org.springframework.stereotype.Component;
import rastreamento.tempo_real.dto.entrega.EntregaResponseDto;
import rastreamento.tempo_real.dto.entregador.EntregadorResumeDto;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoResumeDto;
import rastreamento.tempo_real.model.EntregaEntity;

@Component
public class EntregaMapper {

    public EntregaResponseDto toResponseDto(EntregaEntity entrega) {

        return new EntregaResponseDto(
                entrega.getId(),
                entrega.getCriadaEm(),
                entrega.getFinalizadaEm(),
                entrega.getStatusEntrega(),
                new EntregadorResumeDto(
                        entrega.getEntregador().getId(),
                        entrega.getEntregador().getNome()
                ),
                entrega.getLocalizacoes()
                        .stream()
                        .map(localizacao -> new LocalizacaoResumeDto(
                                localizacao.getId(),
                                localizacao.getRegistradaEm()
                        )).toList()
        );
    }
}