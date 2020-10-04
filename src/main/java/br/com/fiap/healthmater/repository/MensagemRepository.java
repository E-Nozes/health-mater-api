package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
}
