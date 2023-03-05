package com.phoenix.implantation.service;

import com.phoenix.implantation.dto.ProductDto;
import com.phoenix.implantation.model.item.Product;
import com.phoenix.implantation.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(this::productToDto).toList();
    }

    private ProductDto productToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
