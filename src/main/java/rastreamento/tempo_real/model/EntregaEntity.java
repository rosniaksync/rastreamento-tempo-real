package rastreamento.tempo_real.model;

import jakarta.persistence.*;
import lombok.*;
import rastreamento.tempo_real.enums.StatusEntrega;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entregas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "criada_em", nullable = false)
    @Builder.Default
    private LocalDateTime criadaEm = LocalDateTime.now();

    @Column(name = "finalizada_em")
    private LocalDateTime finalizadaEm;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_entrega", nullable = false)
    private StatusEntrega statusEntrega;

    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private EntregadorEntity entregador;

    @OneToMany(mappedBy = "entrega")
    @Builder.Default
    private List<LocalizacaoEntity> localizacoes = new ArrayList<>();
}