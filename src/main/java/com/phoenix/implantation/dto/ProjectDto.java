package com.phoenix.implantation.dto;

import com.phoenix.implantation.model.project.StatusProject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Leticia lima
 * @email leticia.silva@arphoenix.com.br
 * @description Classe de resposta e requisição relacionada ao projeto
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate dateDeliveryPreview;
    private LocalDate dateDelivery;
    private StatusProject status;
    private String userKey;
}
