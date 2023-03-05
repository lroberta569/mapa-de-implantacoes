package com.phoenix.implantation.controller;

import com.phoenix.implantation.dto.ProjectDto;
import com.phoenix.implantation.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leticia lima
 * @email leticia.silva@arphoenix.com.br
 * @description Endpoints relacionados ao projeto
 **/
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Endpoint para consultar projeto por id
     *
     * @param id
     */
    @Operation(summary = "Consultar um projeto por id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar operação")})
    @GetMapping(value = "/{id}", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar o projeto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao busca o projeto");
        }
    }

    /**
     * Endpoint para consultar todos os projetos
     */
    @Operation(summary = "Consultar todos os projetos")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar operação")})
    @GetMapping(value = "/all", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findAll() {
        try {
            return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar a lista de projetos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar a lista de projetos");
        }
    }

    /**
     * Endpoint para cadastrar projetos
     *
     * @param projectDto
     */
    @Operation(summary = "Cadastrar um projeto")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar operação")})
    @PostMapping(value = "/create", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity create(@RequestBody ProjectDto projectDto) {
        try {
            return new ResponseEntity<>(projectService.create(projectDto), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao cadastrar o projeto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao cadastrar o projeto");
        }
    }

    /**
     * Endpoint para Atualizar um projeto
     *
     * @param id
     * @param projectDto
     */
    @Operation(summary = "Atualizar um projeto")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar operação")})
    @PutMapping(value = "/update/{id}", produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProjectDto projectDto) {
        try {
            return new ResponseEntity<>(projectService.update(id, projectDto), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao atualizar o projeto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao atualizar o projeto");
        }
    }
}