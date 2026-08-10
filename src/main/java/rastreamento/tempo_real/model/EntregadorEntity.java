package rastreamento.tempo_real.model;

import jakarta.persistence.*;
import lombok.*;
import rastreamento.tempo_real.enums.StatusEntregador;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntregadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntregador statusEntregador;
}