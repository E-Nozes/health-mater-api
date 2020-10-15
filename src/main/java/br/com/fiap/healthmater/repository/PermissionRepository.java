package br.com.fiap.healthmater.repository;

import br.com.fiap.healthmater.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for {@link Permission}.
 *
 * @author Gabriel Oliveira
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
