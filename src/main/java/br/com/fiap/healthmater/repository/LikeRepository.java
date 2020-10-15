package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Like}.
 *
 * @author Gabriel Oliveira
 */
public interface LikeRepository extends JpaRepository<Like, Integer> {
}
