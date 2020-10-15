package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Comment}.
 *
 * @author Gabriel Oliveira
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
