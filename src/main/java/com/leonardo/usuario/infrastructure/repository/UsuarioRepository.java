package com.leonardo.usuario.infrastructure.repository;


import com.leonardo.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //script de banco de dados = verifica se o retorno passado já existe no banco de dados
    boolean existsByEmail(String email);

    //evita o retorno de informações nulas. Ao inves de NullPointerException (Quebra o código)

    Optional<Usuario> findByEmail(String email);

    //anotação que ajuda a não causar erros durante a exclusão
    @Transactional
    void deleteByEmail(String email);


}
