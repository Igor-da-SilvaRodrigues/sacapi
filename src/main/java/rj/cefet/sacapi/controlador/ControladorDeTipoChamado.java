package rj.cefet.sacapi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.TipoChamadoGetDto;
import rj.cefet.sacapi.dto.TipoChamadoPostDto;
import rj.cefet.sacapi.servico.ServicoDeTipoChamado;

import java.util.List;

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
        //converter o dto em entidade e lista de motivos
        var saved = servicoDeTipoChamado.salvar(
                tipoChamadoPostDto.toTipoChamado(),
                tipoChamadoPostDto.motivos()
            );
        var tipoChamadoGetDto = TipoChamadoGetDto.fromTipoChamado(saved);
        return ResponseEntity.ok().body(tipoChamadoGetDto);
    }

    @GetMapping
    ResponseEntity<List<TipoChamadoGetDto>> getAllTipoChamado(){
        return ResponseEntity.ok().body(servicoDeTipoChamado.findAllTipoChamado().stream().map(TipoChamadoGetDto::fromTipoChamado).toList());
    }
}
