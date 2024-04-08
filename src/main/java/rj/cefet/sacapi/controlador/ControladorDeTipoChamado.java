package rj.cefet.sacapi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.TipoChamadoGetDto;
import rj.cefet.sacapi.dto.TipoChamadoPostDto;
import rj.cefet.sacapi.modelo.TipoChamado;
import rj.cefet.sacapi.servico.ServicoDeTipoChamado;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "", maxAge = 3600)
@RequestMapping("/tipochamado")
public class ControladorDeTipoChamado {
    private ServicoDeTipoChamado servicoDeTipoChamado;

    public ControladorDeTipoChamado(ServicoDeTipoChamado servicoDeTipoChamado) {
        this.servicoDeTipoChamado = servicoDeTipoChamado;
    }

    @PostMapping
    ResponseEntity<TipoChamadoGetDto> criarTipoChamado(@RequestBody TipoChamadoPostDto tipoChamadoPostDto){
        var tipoChamado = tipoChamadoPostDto.toTipoChamado();
        return ResponseEntity.ok().body(
                TipoChamadoGetDto.fromTipoChamado(servicoDeTipoChamado.salvar(tipoChamado))
        );
    }

    @GetMapping
    ResponseEntity<List<TipoChamadoGetDto>> getAllTipoChamado(){
        return ResponseEntity.ok().body(servicoDeTipoChamado.findAllTipoChamado().stream().map(TipoChamadoGetDto::fromTipoChamado).toList());
    }

    @DeleteMapping("/{idTipoChamado}")
    ResponseEntity<String> arquivarTipoChamado(@PathVariable String idTipoChamado){
        var tipoChamadoOptional = servicoDeTipoChamado.arquivarChamadoById(idTipoChamado);
        return tipoChamadoOptional.isPresent() ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/ativo")
    ResponseEntity<List<TipoChamadoGetDto>> getTiposAtivos(){
        return ResponseEntity.ok().body(servicoDeTipoChamado.findTipoChamadoByArquivado(false).stream().map(TipoChamadoGetDto::fromTipoChamado).toList());
    }
}
