package rastreamento.tempo_real.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rastreamento.tempo_real.model.EntregadorEntity;

public interface EntregadorRepository extends JpaRepository<EntregadorEntity, Long> {
}