package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {
}
