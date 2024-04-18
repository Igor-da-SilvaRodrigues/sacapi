package rj.cefet.sacapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;
import rj.cefet.sacapi.servico.ServicoDeTipoChamado;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ChamadoPostDto(
        @NotBlank
        String idTipoChamado,
        @NotBlank
        String idMotivo,
        @NotBlank
        String justificativa,
        @NotNull
        LocalDate dataAbertura,//péssima ideia, a data de abertura deveria ser gerada pela API.
        @NotBlank
        String idDiscente
) {
}
