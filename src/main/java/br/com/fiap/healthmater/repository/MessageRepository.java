package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Message}.
 *
 * @author Gabriel Oliveira
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
