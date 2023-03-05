package com.phoenix.implantation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Classe de resposta e requisição relacionada ao tipo do item
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeItemDto {

    private Long id;
    private String name;
}
