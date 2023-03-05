package com.phoenix.implantation.repository;

import com.phoenix.implantation.model.bundle.Bundle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Repositorio relacionado ao pacote
 */
@Repository
public interface BundleRepository extends JpaRepository<Bundle, Long>, JpaSpecificationExecutor<Bundle> {

    Page<Bundle> findByProjectId(Long id, Pageable pageable);

    List<Bundle> findByProjectId(Long id);

    List<Bundle> findByProjectIdAndObsolete(Long id, boolean obsolete);

    Page<Bundle> findByProjectIdAndObsolete(Long id, boolean obsolete, Pageable pageable);


}

