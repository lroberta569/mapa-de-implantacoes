package com.phoenix.implantation.model.bundle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Entidade relacionada ao status do pacote
 */
@Entity
@Table(name = "STATUS_PACOTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusBundle {

    @Schema(description = "Código do status do pacote")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "STATUS_PACOTE_ID")
    private Long id;

    @Schema(description = "Nome do status do pacote")
    @Column(name = "NOME")
    private String name;
}
