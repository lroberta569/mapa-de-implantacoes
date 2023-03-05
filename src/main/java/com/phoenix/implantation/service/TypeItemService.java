package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.TypeItemDto;
import com.phoenix.implantation.exception.InternalServerErrorException;
import com.phoenix.implantation.model.item.TypeItem;
import com.phoenix.implantation.repository.TypeItemRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Serviços relacionados ao tipo do item
 */
@Log4j2
@Service
public class TypeItemService {

    @Autowired
    private TypeItemRepository typeItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Método para listar os tipos de item
     *
     * @return List<TypeItemDto>
     */
    public List<TypeItemDto> findAll() throws InternalServerErrorException {
        try {
            List<TypeItemDto> listType = typeItemRepository.findAll().stream().map(this::typeItemToDto).toList();
            return listType;
        } catch (Exception e) {
            log.error(e);
            throw new InternalServerErrorException("Nenhum tipo encontrado");
        }
    }

    private TypeItemDto typeItemToDto(TypeItem typeItem) {
        return this.modelMapper.map(typeItem, TypeItemDto.class);
    }
}
