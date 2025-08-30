package com.leonardo.usuario.infrastructure.controller;


import com.leonardo.usuario.infrastructure.business.DTO.EnderecoDTO;
import com.leonardo.usuario.infrastructure.business.DTO.TelefoneDTO;
import com.leonardo.usuario.infrastructure.business.DTO.UsuarioDTO;
import com.leonardo.usuario.infrastructure.business.UsuarioService;
import com.leonardo.usuario.infrastructure.entity.Usuario;
import com.leonardo.usuario.infrastructure.repository.TelefoneRepository;
import com.leonardo.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario (@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (usuarioDTO.getEmail(), usuarioDTO.getSenha()
                ));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity <UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deleteByEmail(email);
        return ResponseEntity.ok().build();
        //como esse metodo não tem retorno, o responseEntity.build gera um retorno caso tiver algum erro
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarDadosUsuario (@RequestBody UsuarioDTO dto, @RequestHeader("Authorization")
                                                             String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco (@RequestBody EnderecoDTO dto, @RequestParam("id") Long id){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaTelefone (@RequestBody TelefoneDTO dto, @RequestParam("id") Long id){

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto));
    }
}
