package rastreamento.tempo_real.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rastreamento.tempo_real.dto.entrega.EntregaRequestDto;
import rastreamento.tempo_real.dto.entrega.EntregaResponseDto;
import rastreamento.tempo_real.dto.entrega.EntregaUpdateDto;
import rastreamento.tempo_real.service.EntregaService;

@RestController
@RequestMapping("/v1/entrega")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService service;

    @PostMapping
    public ResponseEntity<EntregaResponseDto> cadastrarEntrega(@RequestBody @Valid EntregaRequestDto dto) {
        EntregaResponseDto entrega = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrega);
    }

    @GetMapping
    public ResponseEntity<Page<EntregaResponseDto>> listarTodas(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponseDto> listarUma(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarUma(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntregaResponseDto> atualizarEntrega(@PathVariable Long id, @RequestBody EntregaUpdateDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntrega(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}