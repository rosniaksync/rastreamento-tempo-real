package rastreamento.tempo_real.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rastreamento.tempo_real.dto.entregador.EntregadorRequestDto;
import rastreamento.tempo_real.dto.entregador.EntregadorResponseDto;
import rastreamento.tempo_real.dto.entregador.EntregadorUpdateDto;
import rastreamento.tempo_real.enums.StatusEntrega;
import rastreamento.tempo_real.enums.StatusEntregador;
import rastreamento.tempo_real.exception.BusinessException;
import rastreamento.tempo_real.mapper.EntregadorMapper;
import rastreamento.tempo_real.model.EntregaEntity;
import rastreamento.tempo_real.model.EntregadorEntity;
import rastreamento.tempo_real.repository.EntregadorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntregadorServiceTest {


    @InjectMocks
    private EntregadorService service;

    @Mock
    private EntregadorRepository repository;

    @Mock
    private EntregadorMapper mapper;

    @Test
    public void deveDeletarEntregadorSemEntregaEmAndamento() {
        EntregaEntity entrega = EntregaEntity
                .builder()
                .id(1L)
                .statusEntrega(StatusEntrega.ENTREGUE)
                .build();

        EntregadorEntity entregador = EntregadorEntity
                .builder()
                .id(1L)
                .nome("Gabriel")
                .telefone("343432423")
                .statusEntregador(StatusEntregador.DISPONIVEL)
                .entregas(List.of(entrega))
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entregador));

        service.deletar(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    public void naoDeveDeletarEntregadorComEntregaEmAndamento() {
        EntregaEntity entrega = EntregaEntity
                .builder()
                .id(1L)
                .statusEntrega(StatusEntrega.EM_ROTA)
                .build();

        EntregadorEntity entregador = EntregadorEntity
                .builder()
                .id(1L)
                .statusEntregador(StatusEntregador.DISPONIVEL)
                .entregas(List.of(entrega))
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entregador));

        assertThrows(BusinessException.class, () ->
                service.deletar(1L));

        verify(repository, never()).deleteById(any());
    }

    @Test
    public void deveAtualizarCorretamente() {
        EntregadorEntity entregador = EntregadorEntity
                .builder()
                .id(1L)
                .statusEntregador(StatusEntregador.DISPONIVEL)
                .build();

        EntregadorUpdateDto dto = new EntregadorUpdateDto(
                "João Atualizado",
                "47988888888",
                StatusEntregador.OFFLINE
        );

        when(repository.findById(1L)).thenReturn(Optional.of(entregador));
        when(repository.save(any(EntregadorEntity.class))).thenReturn(entregador);

        service.atualizar(1L, dto);

        assertEquals("João Atualizado", entregador.getNome());
        assertEquals("47988888888", entregador.getTelefone());
        assertEquals(StatusEntregador.OFFLINE, entregador.getStatusEntregador());

        verify(repository).save(entregador);
    }
}