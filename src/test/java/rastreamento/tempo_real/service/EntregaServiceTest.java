package rastreamento.tempo_real.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rastreamento.tempo_real.enums.StatusEntrega;
import rastreamento.tempo_real.exception.BusinessException;
import rastreamento.tempo_real.mapper.EntregaMapper;
import rastreamento.tempo_real.model.EntregaEntity;
import rastreamento.tempo_real.repository.EntregaRepository;
import rastreamento.tempo_real.repository.EntregadorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

    @Mock
    private EntregaRepository repository;

    @Mock
    private EntregaMapper mapper;

    @Mock
    private EntregadorRepository entregadorRepository;

    @InjectMocks
    private EntregaService service;

    @Test
    public void deveDeletarEntregaEntregue() {
        EntregaEntity entrega = EntregaEntity
                .builder()
                .id(1L)
                .statusEntrega(StatusEntrega.ENTREGUE)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entrega));

        service.deletar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    public void naoDeveDeletarEntregaEmAndamento() {
        EntregaEntity entrega = EntregaEntity
                .builder()
                .id(1L)
                .statusEntrega(StatusEntrega.PENDENTE)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entrega));

        assertThrows(BusinessException.class, () ->
                service.deletar(1L));

        verify(repository, never()).deleteById(any());
    }
}
