package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Profile}.
 *
 * @author Gabriel Oliveira
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
