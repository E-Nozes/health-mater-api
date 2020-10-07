package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
