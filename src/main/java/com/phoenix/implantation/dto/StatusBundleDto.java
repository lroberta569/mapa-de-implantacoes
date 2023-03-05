package com.phoenix.implantation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Classe de resposta e requisição relacionada ao status do pacote
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusBundleDto {

    private Long id;
    private String name;
}
