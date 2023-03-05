package com.phoenix.implantation.dto;

import com.phoenix.implantation.model.bundle.StatusBundle;
import com.phoenix.implantation.model.project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Classe de resposta e requisição relacionada ao filtro
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterBundleDto {

    private LocalDate dateDeliveryPreview;
    private LocalDate dateDelivery;
    private StatusBundle status;
    private Project project;
}
