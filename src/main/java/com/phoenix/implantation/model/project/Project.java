package com.phoenix.implantation.model.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phoenix.implantation.model.bundle.Bundle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Leticia lima
 * @email leticia.silva@arphoenix.com.br
 * @description Entidade relacionada ao projeto
 **/
@Data
@Entity
@Table(name = "PROJETO")
@AllArgsConstructor
@NoArgsConstructor
public class Project {


    @Schema(description = "Código do projeto")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PROJETO_ID")
    private Long id;


    @Schema(description = "Nome do projeto")
    @Column(name = "NOME")
    private String name;

    @Schema(description = "Descricao do projeto")
    @Column(name = "DESCRICAO")
    private String description;

    @Schema(description = "Data de entrega prevista do projeto")
    @Column(name = "DATA_ENTREGA_PREVISTA")
    private LocalDate dateDeliveryPreview;

    @Schema(description = "Data em que o projeto sera/foi entregue")
    @Column(name = "DATA_ENTREGA")
    private LocalDate dateDelivery;

    @Schema(description = "Status do projeto")
    @ManyToOne
    @JoinColumn(name = "STATUS_PROJETO_ID")
    private StatusProject status;

    @Schema(description = "Lista de pacotes pertencentes ao projeto")
    @Column(name = "USER_KEY")
    private String userKey;


    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<Bundle> bundle;

}