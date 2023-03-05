package com.phoenix.implantation.repository;

import com.phoenix.implantation.model.project.StatusProject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 *
 * @description Repositorio relacionado ao status de projeto
 * */
public interface StatusProjectRepository extends JpaRepository<StatusProject, Long> {
}
