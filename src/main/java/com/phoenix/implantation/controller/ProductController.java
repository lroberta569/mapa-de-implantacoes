package com.phoenix.implantation.controller;

import com.phoenix.implantation.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 *
 * @description Endpoints relacionados ao produto
 * */
@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    /**
     * Endpoint para consultar todos os produtos
     *
     * */
    @Operation(summary = "Consultar todos os produtos")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/all", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
        }
        catch (Exception e){
            log.error("Occoreu um erro ao buscar os produtos",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Occoreu um erro ao buscar os produtos");
        }
    }
}
