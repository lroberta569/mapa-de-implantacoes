package com.phoenix.implantation.repository;

import com.phoenix.implantation.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Leticia lima
 * @email leticia.silva@arphoenix.com.br
 * @description Repositorio relacionados ao projeto
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

}
