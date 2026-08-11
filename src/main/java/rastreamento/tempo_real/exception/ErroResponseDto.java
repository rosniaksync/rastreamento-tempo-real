package rastreamento.tempo_real.exception;

import java.time.LocalDateTime;

public record ErroResponseDto(int status,
                              String mensagem,
                              LocalDateTime timestamp) {}