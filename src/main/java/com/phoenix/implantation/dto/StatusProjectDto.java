package com.phoenix.implantation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Classe de resposta e requisição relacionada ao statusProject
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusProjectDto {

    private Long id;
    private String name;
}
