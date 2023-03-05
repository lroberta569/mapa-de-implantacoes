package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.StatusProjectDto;
import com.phoenix.implantation.exception.InternalServerErrorException;
import com.phoenix.implantation.model.project.StatusProject;
import com.phoenix.implantation.repository.StatusProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 *
 * @description service relacionado ao status de projeto
 * */
@Log4j2
@Service
@RequiredArgsConstructor
public class StatusProjectService {

    private final StatusProjectRepository statusProjectRepository;
    private final ModelMapper modelMapper;
    /**
     *Método para consultar todos os status de um projeto com lista
     *
     * @throws InternalServerErrorException
     * */
    public List<StatusProjectDto> findAll() throws InternalServerErrorException {
        try {
            return statusProjectRepository.findAll().stream().map(this::statusProjectToDto).toList();
        }
        catch (Exception e){
            log.error("Ocorreu um erro ao consultar todos os status de um projeto",e);
            throw new InternalServerErrorException("Ocorreu um erro ao consultar todos os status de um projeto");
        }
    }

    /**
     * Método para converter statusProject em dto
     *
     * @param statusProject
     * */
    private StatusProjectDto statusProjectToDto(StatusProject statusProject) throws InternalServerErrorException {
        try {
            return this.modelMapper.map(statusProject, StatusProjectDto.class);
        }
        catch (Exception e){
            log.error("Ocorreu um erro na conversão de statusProject em statusProjectDto",e);
            throw new InternalServerErrorException("Ocorreu um erro na conversão de statusProject em statusProjectDto");
        }
    }
}
