package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
}
