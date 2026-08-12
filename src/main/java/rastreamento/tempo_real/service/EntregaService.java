package rastreamento.tempo_real.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rastreamento.tempo_real.dto.entrega.EntregaRequestDto;
import rastreamento.tempo_real.dto.entrega.EntregaResponseDto;
import rastreamento.tempo_real.dto.entrega.EntregaUpdateDto;
import rastreamento.tempo_real.enums.StatusEntrega;
import rastreamento.tempo_real.exception.BusinessException;
import rastreamento.tempo_real.exception.ResourceNotFoundException;
import rastreamento.tempo_real.mapper.EntregaMapper;
import rastreamento.tempo_real.model.EntregaEntity;
import rastreamento.tempo_real.model.EntregadorEntity;
import rastreamento.tempo_real.repository.EntregaRepository;
import rastreamento.tempo_real.repository.EntregadorRepository;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository repository;
    private final EntregaMapper mapper;
    private final EntregadorRepository entregadorRepository;

    public EntregaResponseDto cadastrar(EntregaRequestDto dto) {
        EntregadorEntity entregador = entregadorRepository
                .findById(dto.entregadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum entregador com esse id"));

        EntregaEntity entrega = EntregaEntity
                .builder()
                .entregador(entregador)
                .statusEntrega(StatusEntrega.PENDENTE)
                .build();

        EntregaEntity entregaSalva = repository.save(entrega);

        return mapper.toResponseDto(entregaSalva);
    }

    public Page<EntregaResponseDto> listarTodas(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    public EntregaResponseDto listarUma(Long id) {
        EntregaEntity entrega = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma entrega com esse id"));

        return mapper.toResponseDto(entrega);
    }

    @Transactional
    public EntregaResponseDto atualizar(Long id, EntregaUpdateDto dto) {
        EntregaEntity entrega = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma entrega com esse id"));

        if (dto.finalizadaEm() != null) {
            entrega.setFinalizadaEm(dto.finalizadaEm());
        }
        if (dto.statusEntrega() != null) {
            entrega.setStatusEntrega(dto.statusEntrega());
        }
        if (dto.entregadorId() != null) {

            EntregadorEntity entregador = entregadorRepository.findById(dto.entregadorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Nenhum entregador com esse id"));
            entrega.setEntregador(entregador);
        }

        EntregaEntity entregaSalva = repository.save(entrega);

        return mapper.toResponseDto(entregaSalva);
    }

    public void deletar(Long id) {
        EntregaEntity entrega = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma entrega com esse id"));

        if (entrega.getStatusEntrega() != StatusEntrega.ENTREGUE) {
            throw new BusinessException("Não é possível excluir uma entrega em andamento");
        }
        repository.deleteById(id);
    }
}