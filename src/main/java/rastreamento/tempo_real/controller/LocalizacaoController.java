package rastreamento.tempo_real.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoRequestDto;
import rastreamento.tempo_real.dto.localizacao.LocalizacaoResponseDto;
import rastreamento.tempo_real.service.LocalizacaoService;

@RestController
@RequestMapping("/v1/localizacao")
@RequiredArgsConstructor
public class LocalizacaoController {

    private final LocalizacaoService service;

    @PostMapping
    public ResponseEntity<LocalizacaoResponseDto> cadastrarLocalizacao(@RequestBody @Valid LocalizacaoRequestDto dto) {
        LocalizacaoResponseDto localizacao = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(localizacao);
    }

    @GetMapping
    public ResponseEntity<Page<LocalizacaoResponseDto>> listarTodas(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoResponseDto> listarUma(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarUma(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocalizacao(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}