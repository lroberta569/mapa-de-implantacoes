package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.ProjectDto;
import com.phoenix.implantation.exception.InternalServerErrorException;
import com.phoenix.implantation.exception.NotFoundException;
import com.phoenix.implantation.model.project.Project;
import com.phoenix.implantation.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    /**
     * Método para consultar projeto por id
     *
     * @param id
     * @return ProjectDto
     * @throws NotFoundException
     */
    public ProjectDto findById(Long id) {
        return projectToDto(projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Projeto não encontrado")));
    }

    /**
     * Método para consultar projetos
     *
     * @return List<ProjectDto>
     * @throws InternalServerErrorException
     */
    public List<ProjectDto> findAll() throws InternalServerErrorException {
        try {
            return projectRepository.findAll().stream().map(this::projectToDto).toList();
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao listar os projetos");
        }
    }

    /**
     * Método para consultar projetos com paginação
     *
     * @return Page<ProjectDto>
     * @throws InternalServerErrorException
     */
    public Page<ProjectDto> findAll(Pageable pageable) throws InternalServerErrorException {
        try {
            return projectRepository.findAll(pageable).map(this::projectToDto);
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao listar os projetos com paginação");
        }
    }


    /**
     * Método para cadastrar um projeto
     *
     * @param projectDto
     * @return ProjectDto
     * @throws InternalServerErrorException
     */
    public ProjectDto create(ProjectDto projectDto) throws InternalServerErrorException {
        try {
            var project = dtoToProject(projectDto);
            return projectToDto(projectRepository.save(project));
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao criar um projeto");
        }
    }

    /**
     * Método para atualizar um projeto
     *
     * @param id
     * @param projectDto
     * @return ProjectDto
     * @throws NotFoundException
     * @throws InternalServerErrorException
     */
    public ProjectDto update(Long id, ProjectDto projectDto) throws NotFoundException, InternalServerErrorException {
        try {
            projectDto.setId(findById(id).getId());
            return create(projectDto);
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao atualizar o projeto");
        }
    }

    /**
     * Método para converter um dto em projeto
     *
     * @param projectDto
     * @return Project
     */
    private Project dtoToProject(ProjectDto projectDto) {
        try {
            return modelMapper.map(projectDto, Project.class);
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao transformar DTO em projeto");
        }
    }

    /**
     * Método para converter um projeto em DTO
     *
     * @param project
     * @return ProjectDto
     */
    private ProjectDto projectToDto(Project project) {
        try {
            return modelMapper.map(project, ProjectDto.class);
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Ocorreu um erro ao transformar projeto em DTO");
        }
    }
}
