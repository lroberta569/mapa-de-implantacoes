package com.phoenix.implantation.controller;

import com.phoenix.implantation.dto.TypeItemDto;
import com.phoenix.implantation.service.TypeItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Endpoints relacionados ao tipo de item
 */
@Log4j2
@RestController
@RequestMapping("/type-item")
@CrossOrigin("*")
public class TypeItemController {

    @Autowired
    private TypeItemService typeItemService;

    /**
     * Endpoint para listar os tipos de item
     */
    @Operation(summary = "Consultar tipo item")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")})
    @GetMapping(value = "/all", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findAll() {
        try {
            List<TypeItemDto> listType = typeItemService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(listType);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar o tipo do item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
