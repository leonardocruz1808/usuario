package com.leonardo.usuario.infrastructure.business.converter;

import com.leonardo.usuario.infrastructure.business.DTO.EnderecoDTO;
import com.leonardo.usuario.infrastructure.business.DTO.TelefoneDTO;
import com.leonardo.usuario.infrastructure.business.DTO.UsuarioDTO;
import com.leonardo.usuario.infrastructure.entity.Endereco;
import com.leonardo.usuario.infrastructure.entity.Telefone;
import com.leonardo.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario (UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    //enderecoDTOS.stream().map(this::paraEndereco).toList(); essa forma recebe os parametros e converte para um objeto,
    //e passa para uma lista posteriormente, com o toList
    public List<Endereco> paraListaEndereco (List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();

    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }


    // ---------------------------------------------------------------------------------------------------//

    public UsuarioDTO paraUsuarioDTO (Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones()))
                .build();
    }

    //enderecoDTOS.stream().map(this::paraEndereco).toList(); essa forma recebe os parametros e converte para um objeto,
    //e passa para uma lista posteriormente, com o toList
    public List<EnderecoDTO> paraListaEnderecoDTO (List<Endereco> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEnderecoDTO).toList();

    }

    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuario, Usuario entity){
        return Usuario.builder()
                //No update, foi passado o nome? Se sim, grava a atualização, se não, busca no banco o nome, segue a logica
                //para todos os métodos
                .nome(usuario.getNome() != null ? usuario.getNome() : entity.getNome())
                .senha(usuario.getSenha() != null ? usuario.getSenha() : entity.getSenha())
                .email(usuario.getEmail() != null ? usuario.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .id(entity.getId())
                .build();
    }
}
