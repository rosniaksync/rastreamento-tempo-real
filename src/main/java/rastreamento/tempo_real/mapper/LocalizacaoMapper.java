package rastreamento.tempo_real.mapper;

import org.springframework.stereotype.Component;
import rastreamento.tempo_real.dto.entrega.EntregaResumeDto;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoResponseDto;
import rastreamento.tempo_real.model.LocalizacaoEntity;

@Component
public class LocalizacaoMapper {

    public LocalizacaoResponseDto toResponseDto(LocalizacaoEntity localizacao) {
        return new LocalizacaoResponseDto(
                localizacao.getId(),
                localizacao.getLatitude(),
                localizacao.getLongitude(),
                localizacao.getRegistradaEm(),
                new EntregaResumeDto(
                        localizacao.getEntrega().getId(),
                        localizacao.getEntrega().getStatusEntrega())
        );
    }
}