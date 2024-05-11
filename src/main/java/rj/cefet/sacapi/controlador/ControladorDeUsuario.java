package rj.cefet.sacapi.controlador;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rj.cefet.sacapi.dto.UsuarioGetDto;
import rj.cefet.sacapi.modelo.Usuario;
import rj.cefet.sacapi.servico.ServicoDeUsuario;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sac/api/usuario")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioGetDto.class))),
        @ApiResponse(responseCode = "403", description = "Token de acesso/sessão não presente ou inválido, ou tempo limite de seção atingido.", content = @Content)
})
@Tag(name = "Usuario", description = "Operações com usuários")
@SecurityRequirement(name = "sacAuth")
public class ControladorDeUsuario {

    private ServicoDeUsuario servicoDeUsuario;

    public ControladorDeUsuario(ServicoDeUsuario servicoDeUsuario) {
        this.servicoDeUsuario = servicoDeUsuario;
    }

    @Operation(summary = "Retorna todos os usuários", description = "Retorna todos os usuários em uma lista de objetos.")
    @GetMapping
    public ResponseEntity<List<UsuarioGetDto>> getAllUsuarios(@ParameterObject Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(
                servicoDeUsuario.findAllUsuario(pageable).map(UsuarioGetDto::fromUsuario).toList()
        );
    }

    @Operation(summary = "Retorna informações de um único usuário", description = "Retorna informações de um único usuário, recebe a matrícula do usuário como parâmetro de URL")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioGetDto> getById(@PathVariable String idUsuario){
        Optional<Usuario> usuarioOptional = servicoDeUsuario.findById(idUsuario);
        return usuarioOptional.map(usuario -> ResponseEntity.ok().body(UsuarioGetDto.fromUsuario(usuario))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
