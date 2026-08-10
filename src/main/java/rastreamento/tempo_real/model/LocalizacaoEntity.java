package rastreamento.tempo_real.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "localizacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    private BigDecimal longitude;

    @Column(name = "registrada_em", nullable = false)
    private LocalDateTime registradaEm = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private EntregaEntity entrega;
}