package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.ChatWatson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link ChatWatson}.
 *
 * @author Gabriel Oliveira
 */
public interface ChatWatsonRepository extends JpaRepository<ChatWatson, Integer> {
}
