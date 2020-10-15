package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Donation}.
 *
 * @author Gabriel Oliveira
 */
public interface DonationRepository extends JpaRepository<Donation, Integer> {
}
