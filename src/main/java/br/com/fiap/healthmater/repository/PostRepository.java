package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Post}.
 *
 * @author Gabriel Oliveira
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
}
