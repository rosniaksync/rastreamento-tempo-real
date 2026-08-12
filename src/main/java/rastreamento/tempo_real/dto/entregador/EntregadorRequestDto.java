package rastreamento.tempo_real.dto.entregador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EntregadorRequestDto(
        @NotBlank(message = "O nome não pode ser vazio")
        String nome,

        @NotBlank(message = "O telefone não pode ser vazio")
        @Size(max = 20)
        String telefone) {}