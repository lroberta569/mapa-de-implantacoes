package com.phoenix.implantation.model.bundle;

import com.phoenix.implantation.model.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Entidade relacionada ao pacote
 */
@Entity
@Table(name = "PACOTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bundle {

    @Schema(description = "Código do pacote")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PACOTE_ID")
    private Long id;

    @Schema(description = "Nome do pacote")
    @Column(name = "NOME")
    private String name;

    @Schema(description = "Descrição do pacote")
    @Column(name = "DESCRICAO")
    private String description;

    @Schema(description = "Versão do pacote")
    @Column(name = "VERSAO")
    private Long version;

    @Schema(description = "Data de entreg prevista do pacote")
    @Column(name = "DATA_ENTREGA_PREVISTA")
    private LocalDate dateDeliveryPreview;

    @Schema(description = "Data de entreg do pacote")
    @Column(name = "DATA_ENTREGA")
    private LocalDate dateDelivery;

    @Schema(description = "Id do status do pacote")
    @ManyToOne
    @JoinColumn(name = "STATUS_PACOTE_ID")
    private StatusBundle status;

    @Schema(description = "Id oo projeto do pacote")
    @ManyToOne
    @JoinColumn(name = "PROJETO_ID")
    private Project project;

    @Schema(description = "Carta de versão do pacote")
    @Column(name = "CARTA_VERSAO")
    private String cardVersion;

    @Schema(description = "False = versão atual do pacote, True = versão do pacote é obsoleta")
    @Column(name = "OBSOLETO")
    private boolean obsolete;
}