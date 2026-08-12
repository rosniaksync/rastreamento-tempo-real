package rastreamento.tempo_real.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoRequestDto;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoResponseDto;
import rastreamento.tempo_real.exception.ResourceNotFoundException;
import rastreamento.tempo_real.mapper.LocalizacaoMapper;
import rastreamento.tempo_real.model.EntregaEntity;
import rastreamento.tempo_real.model.LocalizacaoEntity;
import rastreamento.tempo_real.repository.EntregaRepository;
import rastreamento.tempo_real.repository.LocalizacaoRepository;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {

    private final LocalizacaoRepository repository;
    private final LocalizacaoMapper mapper;
    private final EntregaRepository entregaRepository;

    public LocalizacaoResponseDto cadastrar(LocalizacaoRequestDto dto) {
        EntregaEntity entrega = entregaRepository
                .findById(dto.entregaId())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma entrega com esse id"));

        LocalizacaoEntity localizacao = LocalizacaoEntity
                .builder()
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .entrega(entrega)
                .build();

        LocalizacaoEntity localizacaoSalva = repository.save(localizacao);

        return mapper.toResponseDto(localizacaoSalva);
    }

    public Page<LocalizacaoResponseDto> listarTodas(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    public LocalizacaoResponseDto listarUma(Long id) {
        LocalizacaoEntity localizacao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma localização com esse id"));

        return mapper.toResponseDto(localizacao);
    }

    public void deletar(Long id) {
        repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Nenhuma localização com esse id"));

        repository.deleteById(id);
    }
}