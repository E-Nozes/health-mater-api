package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Address}.
 *
 * @author Gabriel Oliveira
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
