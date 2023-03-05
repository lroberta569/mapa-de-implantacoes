package com.phoenix.implantation.repository;

import com.phoenix.implantation.model.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Juan Campos do Nascimento
 * @email juan.nascimento@arphoenix.com.br
 * @description Repositorio relacionado ao item;
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByBundleId(Long id, Pageable pageable);

    List<Item> findByBundleId(Long id);

    List<Item> findByBundleIdAndObsolete(Long id, boolean obsolete);

    Page<Item> findByBundleIdAndObsolete(Long id, boolean obsolete, Pageable pageable);

}