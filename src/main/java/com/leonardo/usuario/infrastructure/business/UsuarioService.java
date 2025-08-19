package com.leonardo.usuario.infrastructure.business;


import com.leonardo.usuario.infrastructure.business.DTO.UsuarioDTO;
import com.leonardo.usuario.infrastructure.business.converter.UsuarioConverter;
import com.leonardo.usuario.infrastructure.entity.Usuario;
import com.leonardo.usuario.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


        //recebe um objeto usuarioDTO
    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        //transformou para usuario entity
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        //salvou no banco de dados
        usuario = usuarioRepository.save(usuario);
        //retornou um usuarioDTO
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
