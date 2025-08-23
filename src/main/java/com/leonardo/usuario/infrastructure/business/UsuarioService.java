package com.leonardo.usuario.infrastructure.business;


import com.leonardo.usuario.infrastructure.business.DTO.UsuarioDTO;
import com.leonardo.usuario.infrastructure.business.converter.UsuarioConverter;
import com.leonardo.usuario.infrastructure.entity.Usuario;
import com.leonardo.usuario.infrastructure.exceptions.ConflictException;
import com.leonardo.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.leonardo.usuario.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

        //recebe um objeto usuarioDTO
    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        //Encriptação da senha
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        //transformou para usuario entity
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        //salvou no banco de dados
        usuario = usuarioRepository.save(usuario);
        //retornou um usuarioDTO
        return usuarioConverter.paraUsuarioDTO(usuario);
    }




    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("Email já cadastrado." + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado" + e.getCause());

        }

    }

    public Usuario buscarUsuarioPorEmail (String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não encontrado." + email));
    }

    public void  deleteByEmail(String email){
        usuarioRepository.deleteByEmail(email);

    }
}
