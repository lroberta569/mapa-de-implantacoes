package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.StatusBundleDto;
import com.phoenix.implantation.exception.InternalServerErrorException;
import com.phoenix.implantation.model.bundle.StatusBundle;
import com.phoenix.implantation.repository.StatusBundleRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Serviços relacionados ao status do pacote
 */
@Log4j2
@Service
public class StatusBundleService {
    @Autowired
    private StatusBundleRepository statusBundleRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Método para listar os status do pacote
     *
     * @return List<StatusBundleDto>
     */
    public List<StatusBundleDto> findAll() throws InternalServerErrorException {
        try {
            List<StatusBundleDto> listStatus = statusBundleRepository.findAll().stream().map(this::statusBundleToDto).toList();
            return listStatus;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum status encontrado");
        }
    }

    private StatusBundleDto statusBundleToDto(StatusBundle statusBundle) {
        return modelMapper.map(statusBundle, StatusBundleDto.class);
    }

}
