package com.phoenix.implantation.repository;

import com.phoenix.implantation.model.item.StatusItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 *
 * @description Repositorio relacionado ao status de item
 * */
public interface StatusItemRepository extends JpaRepository<StatusItem, Long> {
}
