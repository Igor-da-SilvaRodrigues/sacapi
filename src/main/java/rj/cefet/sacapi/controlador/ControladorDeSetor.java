package rj.cefet.sacapi.controlador;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.SetorGetDto;
import rj.cefet.sacapi.dto.SetorPostDto;
import rj.cefet.sacapi.servico.ServicoDeSetor;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sac/api/setor")
public class ControladorDeSetor {
    private ServicoDeSetor servicoDeSetor;

    public ControladorDeSetor(ServicoDeSetor servicoDeSetor) {
        this.servicoDeSetor = servicoDeSetor;
    }

    @GetMapping
    public ResponseEntity<List<SetorGetDto>> getAllSetores(Pageable pageable){
        return ResponseEntity.ok().body(
                servicoDeSetor.findAllSetores(pageable).map(SetorGetDto::fromSetor).toList()
        );
    }

    @PostMapping
    public ResponseEntity<SetorGetDto> criarSetor(@RequestBody @Valid SetorPostDto setorPostDto){
        SetorGetDto response = SetorGetDto.fromSetor(servicoDeSetor.salvar(setorPostDto.toSetor()));
        return ResponseEntity.ok().body(response);
    }
}
