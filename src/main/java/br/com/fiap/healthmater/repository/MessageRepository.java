package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
