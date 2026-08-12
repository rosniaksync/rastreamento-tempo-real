package rastreamento.tempo_real.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rastreamento.tempo_real.dto.entregador.EntregadorRequestDto;
import rastreamento.tempo_real.dto.entregador.EntregadorResponseDto;
import rastreamento.tempo_real.dto.entregador.EntregadorUpdateDto;
import rastreamento.tempo_real.service.EntregadorService;

@RestController
@RequestMapping("/v1/entregador")
@RequiredArgsConstructor
public class EntregadorController {

    private final EntregadorService service;

    @PostMapping
    public ResponseEntity<EntregadorResponseDto> cadastrarEntregador(@RequestBody @Valid EntregadorRequestDto dto) {
        EntregadorResponseDto entregador = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entregador);
    }

    @GetMapping
    public ResponseEntity<Page<EntregadorResponseDto>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregadorResponseDto> listarUm(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarUm(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntregadorResponseDto> atualizarEntregador(@PathVariable Long id, @RequestBody EntregadorUpdateDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntregador(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}