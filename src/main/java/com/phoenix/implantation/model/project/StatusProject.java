package com.phoenix.implantation.model.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "STATUS_PROJETO")
@AllArgsConstructor
@NoArgsConstructor
public class StatusProject {
    @Schema(description = "Id do status do projeto")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "STATUS_PROJETO_ID")
    private Long id;
    @Schema(description = "Nome do status")
    @Column(name = "NOME")
    private String name;
}
