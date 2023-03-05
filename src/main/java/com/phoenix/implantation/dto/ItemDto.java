package com.phoenix.implantation.dto;


import com.phoenix.implantation.model.bundle.Bundle;
import com.phoenix.implantation.model.item.Product;
import com.phoenix.implantation.model.item.StatusItem;
import com.phoenix.implantation.model.item.TypeItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Classe de resposta e requisição relacionada ao item
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private StatusItem status;
    private Long version;
    private String description;
    private String name;
    private Bundle bundle;
    private TypeItem type;
    private Product product;
    private Boolean incrementVersion;
}

