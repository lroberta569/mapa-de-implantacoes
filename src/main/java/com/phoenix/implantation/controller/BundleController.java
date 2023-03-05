package com.phoenix.implantation.controller;

import com.phoenix.implantation.dto.BundleDto;
import com.phoenix.implantation.dto.FilterBundleDto;
import com.phoenix.implantation.service.BundleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Endpoints relacionados ao pacote
 */
@Log4j2
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/bundle")
public class BundleController {

    @Autowired
    private BundleService bundleService;

    /**
     * Endpoint para consultar o projeto pelo id
     *
     * @param id
     */
    @Operation(summary = "Consultar um pacote por id")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/{id}", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            BundleDto returnedBundle = bundleService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(returnedBundle);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar o pacote", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Endpoint para listar todos os pacotes com paginação pelo id do projeto
     *
     * @param id
     * @param pageable
     */
    @Operation(summary = "Consultar Pacotes Lista com paginação")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/all", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findByProjectId(@RequestParam Long id, Pageable pageable) {
        try {
            Page<BundleDto> returnedBundles = bundleService.findByProjectId(id, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(returnedBundles);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar os pacotes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Endpoint para listar todas as ultimas versões do pacotes com paginação pelo id do projeto
     *
     * @param id
     * @param pageable
     */
    @Operation(summary = "Consultar Pacotes lista ultima versão com paginação")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/allByObsoletePage", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity findByObsolete(@RequestParam Long id, Pageable pageable) {
        try {
            Page<BundleDto> returnedBundles = bundleService.findByObsolete(id, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(returnedBundles);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar os pacotes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Endpoint para listar todos os pacotes pelo id do projeto
     *
     * @param id
     */
    @Operation(summary = "Consultar Pacotes lista")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping(value = "/allByProject", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findBundlesByProjectId(@RequestParam Long id) {
        try {
            List<BundleDto> returnedBundles = bundleService.findBundleByProjectId(id);
            return ResponseEntity.status(HttpStatus.OK).body(returnedBundles);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar os pacotes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Endpoint para listar todas as ultimas versoes do pacote pelo id do projeto
     *
     * @param id
     */
    @Operation(summary = "Consultar Pacotes lista ultima versão")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/allByObsoleteList", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findByObsolete(@RequestParam Long id) {
        try {
            List<BundleDto> returnedBundles = bundleService.findByObsolete(id);
            return ResponseEntity.status(HttpStatus.OK).body(returnedBundles);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar os pacotes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Endpoint para cadastrar pacotes
     *
     * @param bundleDto
     */
    @Operation(summary = "Cadastrar pacote")
    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    @PostMapping(value = "/create", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity register(@RequestBody BundleDto bundleDto) {
        try {
            BundleDto registerBundles = bundleService.create(bundleDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerBundles);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao cadastrar o pacote", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Endpoint para atualizar o pacote
     *
     * @param id
     * @param bundleDto
     */
    @Operation(summary = "Atualizar o pacote")
    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    @PutMapping(value = "/update/{id}", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody BundleDto bundleDto) {
        try {
            BundleDto registerBundles = bundleService.update(id, bundleDto);
            return ResponseEntity.status(HttpStatus.OK).body(registerBundles);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao atualizar o pacote", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * Endpoint para filtrar pacotes
     *
     * @param filterBundleDto
     */
    @Operation(summary = "Consultar Pacotes lista pelo filtro")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @PostMapping(value = "/filter", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity filter(@RequestBody FilterBundleDto filterBundleDto) {
        try {
            List<BundleDto> filteredBundles = bundleService.findBundleByFilter(filterBundleDto);
            return ResponseEntity.status(HttpStatus.OK).body(filteredBundles);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar os pacotes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}