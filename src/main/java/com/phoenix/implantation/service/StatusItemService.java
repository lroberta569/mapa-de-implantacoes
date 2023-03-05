package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.StatusItemDto;
import com.phoenix.implantation.model.item.StatusItem;
import com.phoenix.implantation.repository.StatusItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusItemService {

    private final StatusItemRepository statusItemRepository;
    private final ModelMapper modelMapper;

    public List<StatusItemDto> findAll() {
        return statusItemRepository.findAll().stream().map(this::statusItemToDto).toList();
    }

    private StatusItemDto statusItemToDto(StatusItem statusItem) {
        return modelMapper.map(statusItem, StatusItemDto.class);
    }
}
