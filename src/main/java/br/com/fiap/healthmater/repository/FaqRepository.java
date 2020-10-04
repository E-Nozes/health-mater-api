package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Integer> {
}
