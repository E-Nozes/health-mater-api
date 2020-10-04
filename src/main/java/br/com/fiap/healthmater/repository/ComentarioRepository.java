package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
