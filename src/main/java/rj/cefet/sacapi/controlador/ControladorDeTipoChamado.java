package rj.cefet.sacapi.controlador;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.TipoChamadoGetDto;
import rj.cefet.sacapi.dto.TipoChamadoPostDto;
import rj.cefet.sacapi.modelo.TipoChamado;
import rj.cefet.sacapi.servico.ServicoDeTipoChamado;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sac/api/tipochamado")
public class ControladorDeTipoChamado {
    private ServicoDeTipoChamado servicoDeTipoChamado;

    public ControladorDeTipoChamado(ServicoDeTipoChamado servicoDeTipoChamado) {
        this.servicoDeTipoChamado = servicoDeTipoChamado;
    }

    @PostMapping
    ResponseEntity<TipoChamadoGetDto> criarTipoChamado(@RequestBody @Valid TipoChamadoPostDto tipoChamadoPostDto){
        var tipoChamado = tipoChamadoPostDto.toTipoChamado();
        return ResponseEntity.ok().body(
                TipoChamadoGetDto.fromTipoChamado(servicoDeTipoChamado.salvar(tipoChamado))
        );
    }

    @GetMapping
    ResponseEntity<List<TipoChamadoGetDto>> getAllTipoChamado(Pageable pageable){
        return ResponseEntity.ok().body(servicoDeTipoChamado.findAllTipoChamado(pageable).map(TipoChamadoGetDto::fromTipoChamado).toList());
    }

    @DeleteMapping("/{idTipoChamado}")
    ResponseEntity<String> arquivarTipoChamado(@PathVariable @NotBlank String idTipoChamado){
        var tipoChamadoOptional = servicoDeTipoChamado.arquivarChamadoById(idTipoChamado);
        return tipoChamadoOptional.isPresent() ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/ativo")
    ResponseEntity<List<TipoChamadoGetDto>> getTiposAtivos(Pageable pageable){
        return ResponseEntity.ok().body(servicoDeTipoChamado.findTipoChamadoByArquivado(false, pageable).map(TipoChamadoGetDto::fromTipoChamado).toList());
    }
}
