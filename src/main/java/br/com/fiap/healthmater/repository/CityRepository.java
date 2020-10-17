package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository class for {@link City}.
 *
 * @author Gabriel Oliveira
 */
public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByNameIgnoreCase(String name);

}
