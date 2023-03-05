package com.phoenix.implantation.controller;

import com.phoenix.implantation.service.StatusProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
 * @description Endpoints relacionados ao status de item
 */
@Log4j2
@RestController
@RequestMapping("/status-project")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatusProjectController {

    private final StatusProjectService statusProjectService;

    /**
     * Endpoint para consultar todos os status de um projeto
     *
     * */
    @Operation(summary = "Consultar status projeto")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar operação")})
    @GetMapping(value = "/all", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(statusProjectService.findAll());
        } catch (Exception e) {
            log.error("Ocorreu um erro ao consultar todos os status de projeto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao consultar todos os status de projeto");
        }
    }
}
