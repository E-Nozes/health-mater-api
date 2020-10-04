package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
