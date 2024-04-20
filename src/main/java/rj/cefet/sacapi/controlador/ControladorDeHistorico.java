package rj.cefet.sacapi.controlador;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.HistoricoGetDto;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.servico.ServicoDeChamado;
import rj.cefet.sacapi.servico.ServicoDeHistorico;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sac/api/historico")
public class ControladorDeHistorico {
    private ServicoDeChamado servicoDeChamado;
    private ServicoDeHistorico servicoDeHistorico;

    public ControladorDeHistorico(ServicoDeChamado servicoDeChamado, ServicoDeHistorico servicoDeHistorico) {
        this.servicoDeChamado = servicoDeChamado;
        this.servicoDeHistorico = servicoDeHistorico;
    }

    @GetMapping("/{protocolo}")
    ResponseEntity<List<HistoricoGetDto>> findHistoricosByChamado(@PathVariable @NotBlank String protocolo){
        Chamado chamado = servicoDeChamado.findById(protocolo);

        return ResponseEntity.ok().body(servicoDeHistorico.getHistoricosByChamado(chamado).stream().map(HistoricoGetDto::fromHistorico).toList());
    }
}
