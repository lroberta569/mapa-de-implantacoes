package com.phoenix.implantation.controller;

import com.phoenix.implantation.dto.ItemDto;
import com.phoenix.implantation.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Juan Campos do Nascimento
 * @email juan.nascimento@arphoenix.com.br
 * @description Endpoints  relacionado ao item;
 */
@Slf4j
@CrossOrigin(value = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    /**
     * Endpoint para consultar o item pelo id do pacote
     *
     * @param id
     */
    @Operation(summary = "Consultar item por id do pacote  ")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/bundle", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity allByBundle(@RequestParam Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.findByBundleId(id));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao consultar o id do pacote", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao consultar o id do pacote");
        }
    }

    /**
     * Endpoint para consultar todos os itens
     *
     * @param id
     * @param pageable
     */
    @Operation(summary = "Consultar todos os  itens")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao reizar a busca")
    @GetMapping(value = "/all", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findAll(@RequestParam Long id, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.findByBundleId(id, pageable));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao consultar o item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao consultar o item");
        }
    }

    /**
     * Endpoint para Consultar itens lista ultima versão
     *
     * @param id
     */
    @Operation(summary = "Consultar itens lista ultima versão")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/allByObsoleteList", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findByObsolete(@RequestParam Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.findByObsolete(id));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao consultar os itens ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao consultar os itens");
        }
    }

    /**
     * Endpoint para Consultar item lista ultima versão com paginação
     *
     * @param id
     * @param pageable
     */
    @Operation(summary = "Consultar item lista ultima vers com paginação ")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/allByObsoletePage", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findByObsolete(@RequestParam Long id, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.findByObsolete(id, pageable));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao consultar os itens", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao consultar os itens");
        }
    }

    /**
     * Endpoint para Consultar item id
     *
     * @param id
     */
    @Operation(summary = "Consultar item id")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @GetMapping(value = "/{id}", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.findById(id));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao consultar o item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao consultar o item");
        }
    }

    /**
     * Endpoint para Cadastrar item
     *
     * @param itemDto
     */
    @Operation(summary = " Cadastrar item ")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @PostMapping(value = "/create", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity create(@RequestBody ItemDto itemDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(itemDto));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao cadastrar o item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao cadastrar o item");
        }
    }

    /**
     * Endpoint para Atualizar item
     *
     * @param id
     * @param itemDto
     */
    @Operation(summary = "Atualizar item")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca")
    @PutMapping(value = "/update/{id}", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.update(id, itemDto));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao atualizar o item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao atualizar o item");
        }
    }

}