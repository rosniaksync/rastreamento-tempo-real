package rastreamento.tempo_real.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rastreamento.tempo_real.model.LocalizacaoEntity;

public interface LocalizacaoRepository extends JpaRepository<LocalizacaoEntity, Long> {
}
