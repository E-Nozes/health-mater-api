package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
