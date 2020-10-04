package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
}
