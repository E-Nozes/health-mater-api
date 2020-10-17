package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link State}.
 *
 * @author Gabriel Oliveira
 */
public interface StateRepository extends JpaRepository<State, Integer> {
}
