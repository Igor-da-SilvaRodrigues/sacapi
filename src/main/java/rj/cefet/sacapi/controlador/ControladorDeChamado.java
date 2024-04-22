package rj.cefet.sacapi.controlador;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.*;
import rj.cefet.sacapi.enums.Status;
import rj.cefet.sacapi.modelo.*;
import rj.cefet.sacapi.servico.*;

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
    private ServicoDeSetor servicoDeSetor;
    private ServicoDeHistorico servicoDeHistorico;

    public ControladorDeChamado(ServicoDeChamado servicoDeChamado, ServicoDeTipoChamado servicoDeTipoChamado, ServicoDeDiscente servicoDeDiscente, ServicoDeSetor servicoDeSetor, ServicoDeHistorico servicoDeHistorico) {
        this.servicoDeChamado = servicoDeChamado;
        this.servicoDeTipoChamado = servicoDeTipoChamado;
        this.servicoDeDiscente = servicoDeDiscente;
        this.servicoDeSetor = servicoDeSetor;
        this.servicoDeHistorico = servicoDeHistorico;
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
        chamado.setStatus(Status.ABERTO.value);
        var retorno = servicoDeChamado.salvar(chamado);
        return ResponseEntity.ok().body(ChamadoPostResposeDto.fromChamado(retorno));
    }

    @PutMapping
    ResponseEntity<String> atualizarChamado(@RequestBody @Valid ChamadoPutDto dto){
        Chamado chamado = servicoDeChamado.findById(dto.protocolo());
        if (chamado.getStatus() == Status.FECHADO.value) return ResponseEntity.badRequest().body("Chamado fechado para alterações");
        boolean changed = false;
        //set setor
        if (dto.idSetor() != null){
            chamado.setSetor(servicoDeSetor.findById(dto.idSetor()));
            changed = true;
        }
        //set parecer
        if (dto.parecer() != null){
            chamado.setParecer(dto.parecer());
            changed = true;
        }
        //set status
        if (dto.status() != null){
            if(dto.status() == Status.FECHADO.value) chamado.setDataFechamento(LocalDateTime.now());
            chamado.setStatus(dto.status());
            changed = true;
        }

        if (changed) {
            servicoDeChamado.salvar(chamado);
            servicoDeHistorico.criarHistoricoDeChamado(chamado);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<ChamadoGetDto>> getAllChamados(Pageable pageable){

        return ResponseEntity.ok().body(servicoDeChamado.findAllChamados(pageable).map(ChamadoGetDto::fromChamado).toList());
    }

    @GetMapping("/{idChamado}")
    ResponseEntity<ChamadoGetByIdDto> getChamadoById(@PathVariable @NotBlank String idChamado){
        return ResponseEntity.ok().body(ChamadoGetByIdDto.fromChamado(servicoDeChamado.findById(idChamado)));
    }

    @GetMapping("/usuario/{idUsuario}")
    ResponseEntity<List<ChamadoGetByUsuarioDto>> getAllChamadosByUsuario(@PathVariable String idUsuario, Pageable pageable){
        return ResponseEntity.ok().body(servicoDeChamado.findByMatriculaDeDiscente(idUsuario, pageable).map(ChamadoGetByUsuarioDto::fromChamado).toList());
    }
}
