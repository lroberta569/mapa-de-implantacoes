package com.phoenix.implantation.repository;

import com.phoenix.implantation.model.item.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 *
 * @description Repositorio relacionado ao produto
 * */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
