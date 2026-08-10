package rastreamento.tempo_real.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rastreamento.tempo_real.dto.EntregadorRequestDto;
import rastreamento.tempo_real.dto.EntregadorResponseDto;
import rastreamento.tempo_real.enums.StatusEntregador;
import rastreamento.tempo_real.mapper.EntregadorMapper;
import rastreamento.tempo_real.model.EntregadorEntity;
import rastreamento.tempo_real.repository.EntregadorRepository;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository repository;
    private final EntregadorMapper mapper;

    public EntregadorResponseDto cadastrarEntregador(EntregadorRequestDto dto) {

        EntregadorEntity entregador = EntregadorEntity
                .builder()
                .nome(dto.nome())
                .telefone(dto.telefone())
                .statusEntregador(StatusEntregador.DISPONIVEL)
                .build();

        EntregadorEntity entregadorSalvo = repository.save(entregador);
        return mapper.toResponseDto(entregadorSalvo);
    }
}
