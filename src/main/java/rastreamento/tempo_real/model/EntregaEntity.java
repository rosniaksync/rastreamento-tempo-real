package rastreamento.tempo_real.model;

import jakarta.persistence.Entity;
import lombok.*;
import rastreamento.tempo_real.enums.StatusEntrega;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntregaEntity {

    private Long id;

    private LocalDateTime criadaEm;

    private LocalDateTime finalizadaEm;

    private StatusEntrega statusEntrega;

    private EntregadorEntity entregadorId;
}
