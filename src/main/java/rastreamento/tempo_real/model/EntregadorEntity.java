package rastreamento.tempo_real.model;

import jakarta.persistence.*;
import lombok.*;
import rastreamento.tempo_real.enums.StatusEntregador;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entregadores")
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
    @Column(name = "status_entregador", nullable = false)
    private StatusEntregador statusEntregador;

    @OneToMany(mappedBy = "entregador")
    @Builder.Default
    private List<EntregaEntity> entregas = new ArrayList<>();
}