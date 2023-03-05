package com.phoenix.implantation.dto;

import com.phoenix.implantation.model.bundle.StatusBundle;
import com.phoenix.implantation.model.project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Classe de resposta e requisição relacionada ao pacote
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BundleDto {
    private Long id;
    private String name;
    private String description;
    private Long version;
    private LocalDate dateDeliveryPreview;
    private LocalDate dateDelivery;
    private StatusBundle status;
    private Project project;
    private Boolean incrementVersion;
}

