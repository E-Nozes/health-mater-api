package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}