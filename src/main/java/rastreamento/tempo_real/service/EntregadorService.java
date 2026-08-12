package rastreamento.tempo_real.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rastreamento.tempo_real.dto.entregador.EntregadorRequestDto;
import rastreamento.tempo_real.dto.entregador.EntregadorResponseDto;
import rastreamento.tempo_real.dto.entregador.EntregadorUpdateDto;
import rastreamento.tempo_real.enums.StatusEntrega;
import rastreamento.tempo_real.enums.StatusEntregador;
import rastreamento.tempo_real.exception.BusinessException;
import rastreamento.tempo_real.exception.ResourceNotFoundException;
import rastreamento.tempo_real.mapper.EntregadorMapper;
import rastreamento.tempo_real.model.EntregadorEntity;
import rastreamento.tempo_real.repository.EntregadorRepository;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository repository;
    private final EntregadorMapper mapper;

    public EntregadorResponseDto cadastrar(EntregadorRequestDto dto) {
        EntregadorEntity entregador = EntregadorEntity
                .builder()
                .nome(dto.nome())
                .telefone(dto.telefone())
                .statusEntregador(StatusEntregador.DISPONIVEL)
                .build();

        EntregadorEntity entregadorSalvo = repository.save(entregador);
        return mapper.toResponseDto(entregadorSalvo);
    }

    public Page<EntregadorResponseDto> listarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    public EntregadorResponseDto listarUm(Long id) {
        EntregadorEntity entregador = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe entregador com esse id"));

        return mapper.toResponseDto(entregador);
    }

    @Transactional
    public EntregadorResponseDto atualizar(Long id, EntregadorUpdateDto dto) {
        EntregadorEntity entregador = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe entregador com esse id"));

        if(dto.nome() != null) {
            entregador.setNome(dto.nome());
        }
        if(dto.telefone() != null) {
            entregador.setTelefone(dto.telefone());
        }
        if(dto.statusEntregador() != null) {
            entregador.setStatusEntregador(dto.statusEntregador());
        }

        EntregadorEntity entregadorSalvo = repository.save(entregador);

        return mapper.toResponseDto(entregadorSalvo);
    }

    public void deletar(Long id) {
        EntregadorEntity entregador = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não existe entregador com esse id"));

        boolean entregaEmAndamento = entregador.getEntregas().stream()
                        .anyMatch(entrega -> entrega.getStatusEntrega() != StatusEntrega.ENTREGUE);

        if(entregaEmAndamento) {
            throw new BusinessException("Não é possivel deletar um entregador com entregas em andamento");
        }

        repository.deleteById(id);
    }
}