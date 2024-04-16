package rj.cefet.sacapi.controlador;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.LoginDto;
import rj.cefet.sacapi.dto.RegisterAdministradorDto;
import rj.cefet.sacapi.dto.RegisterDiscenteDto;
import rj.cefet.sacapi.modelo.Administrador;
import rj.cefet.sacapi.modelo.Discente;
import rj.cefet.sacapi.modelo.Setor;
import rj.cefet.sacapi.modelo.Usuario;
import rj.cefet.sacapi.seguranca.servico.ServicoDeTokens;
import rj.cefet.sacapi.servico.ServicoDeUsuario;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sac/api")
public class ControladorDeAutenticacao {

    private AuthenticationManager authenticationManager;

    private ServicoDeUsuario servicoDeUsuario;
    private ServicoDeTokens servicoDeTokens;

    public ControladorDeAutenticacao(AuthenticationManager authenticationManager, ServicoDeUsuario servicoDeUsuario, ServicoDeTokens servicoDeTokens) {
        this.authenticationManager = authenticationManager;
        this.servicoDeUsuario = servicoDeUsuario;
        this.servicoDeTokens = servicoDeTokens;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.matricula(), loginDto.senha());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = servicoDeTokens.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register/discente")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDiscenteDto dto){
        Discente discente = new Discente();
        discente.setCep(dto.cep());
        discente.setEmail(dto.email());
        discente.setEndereco(dto.endereco());
        discente.setNome(dto.nome());
        discente.setSenha(new BCryptPasswordEncoder().encode(dto.senha()));//armazena a senha criptografada
        discente.setTelefone(dto.telefone());
        discente.setUsuarioAdm(false);

        this.servicoDeUsuario.salvar(discente);

        return ResponseEntity.ok().body(discente.getMatricula());
    }

    @PostMapping("/register/administrador")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> registerAdmin(@RequestBody @Valid RegisterAdministradorDto dto){
        Administrador administrador = new Administrador();
        administrador.setCep(dto.cep());
        administrador.setEmail(dto.email());
        administrador.setEndereco(dto.endereco());
        administrador.setNome(dto.nome());
        administrador.setSenha(new BCryptPasswordEncoder().encode(dto.senha()));
        administrador.setTelefone(dto.telefone());
        administrador.setUsuarioAdm(true);
        administrador.setSetor(new Setor(dto.setor()));

        return ResponseEntity.ok().body(servicoDeUsuario.salvarAdmin(administrador).getMatricula());
    }
}
