package com.phoenix.implantation.repository;

import com.phoenix.implantation.model.bundle.StatusBundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Repositorio relacionado ao status do pacote
 */
@Repository
public interface StatusBundleRepository extends JpaRepository<StatusBundle, Long> {
}
