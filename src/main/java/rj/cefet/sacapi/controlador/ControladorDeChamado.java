package rj.cefet.sacapi.controlador;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.ChamadoGetByIdDto;
import rj.cefet.sacapi.dto.ChamadoGetDto;
import rj.cefet.sacapi.dto.ChamadoPostResposeDto;
import rj.cefet.sacapi.dto.ChamadoPostDto;
import rj.cefet.sacapi.modelo.Chamado;
import rj.cefet.sacapi.modelo.Discente;
import rj.cefet.sacapi.modelo.Motivo;
import rj.cefet.sacapi.modelo.TipoChamado;
import rj.cefet.sacapi.servico.ServicoDeChamado;
import rj.cefet.sacapi.servico.ServicoDeDiscente;
import rj.cefet.sacapi.servico.ServicoDeTipoChamado;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sac/api/chamado")
public class ControladorDeChamado {
    private ServicoDeChamado servicoDeChamado;
    private ServicoDeTipoChamado servicoDeTipoChamado;
    private ServicoDeDiscente servicoDeDiscente;

    public ControladorDeChamado(ServicoDeChamado servicoDeChamado, ServicoDeTipoChamado servicoDeTipoChamado, ServicoDeDiscente servicoDeDiscente) {
        this.servicoDeChamado = servicoDeChamado;
        this.servicoDeTipoChamado = servicoDeTipoChamado;
        this.servicoDeDiscente = servicoDeDiscente;
    }

    @PostMapping
    ResponseEntity<ChamadoPostResposeDto> criarChamado(@RequestBody @Valid ChamadoPostDto chamadoPostDto){
        //resgatando tipo de chamado e discente, e retornando 404 caso não encontrados
        TipoChamado tipoChamado = servicoDeTipoChamado.findById(chamadoPostDto.idTipoChamado());
        Discente discente = servicoDeDiscente.findById(chamadoPostDto.idDiscente());

        Chamado chamado = new Chamado();
        //vou usar UUID msm, professor que reclame depois.
        chamado.setProtocolo(UUID.randomUUID().toString());
        chamado.setTipoChamado(tipoChamado);
        chamado.setDataAbertura(LocalDateTime.now());
        //'new Motivo()' funciona aqui, pois Motivo sobrescreveu o método '.equals()', quaisquer motivos que contenham os mesmos ids e tipos de chamado
        //são considerados iguais. Dessa forma, o método '.contains()' do set irá identificar este novo motivo como válido.
        chamado.setMotivo(new Motivo(chamadoPostDto.idMotivo(), tipoChamado));
        chamado.setDiscente(discente);
        chamado.setJustificativa(chamadoPostDto.justificativa());

        var retorno = servicoDeChamado.salvar(chamado);
        return ResponseEntity.ok().body(ChamadoPostResposeDto.fromChamado(retorno));
    }
    @GetMapping
    ResponseEntity<List<ChamadoGetDto>> getAllChamados(){
        return ResponseEntity.ok().body(servicoDeChamado.findAllChamados().stream().map(ChamadoGetDto::fromChamado).toList());
    }

    @GetMapping("/{idChamado}")
    ResponseEntity<ChamadoGetByIdDto> getChamadoById(@PathVariable @NotBlank String idChamado){
        return ResponseEntity.ok().body(ChamadoGetByIdDto.fromChamado(servicoDeChamado.findById(idChamado)));
    }

    @GetMapping("/usuario/{idUsuario}")
    ResponseEntity<String> getAllChamadosByUsuario(@PathVariable String idUsuario){
        servicoDeChamado.findByMatriculaDeDiscente(idUsuario);
        return ResponseEntity.ok().body("Ler log");
    }
}
