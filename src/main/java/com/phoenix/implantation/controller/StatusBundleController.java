package com.phoenix.implantation.controller;

import com.phoenix.implantation.dto.StatusBundleDto;
import com.phoenix.implantation.service.StatusBundleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
 * @description Endpoints relacionados ao status do pacote
 */
@Log4j2
@RestController
@RequestMapping("/status-bundle")
@CrossOrigin("*")
public class StatusBundleController {

    @Autowired
    private StatusBundleService statusBundleService;

    /**
     * Endpoint para listar os status do pacote
     */
    @Operation(summary = "Consultar todos os status de pacote")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/all", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findAll() {
        try {
            List<StatusBundleDto> listStatus = statusBundleService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(listStatus);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar o status do pacote", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
