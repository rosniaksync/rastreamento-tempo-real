package rastreamento.tempo_real.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rastreamento.tempo_real.model.EntregaEntity;

public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {
}
