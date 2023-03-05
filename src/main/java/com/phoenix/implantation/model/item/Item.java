package com.phoenix.implantation.model.item;

import com.phoenix.implantation.model.bundle.Bundle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Juan Campos do Nascimento
 * @email juan.nascimento@arphoenix.com.br
 * @description Endpoints  relacionado ao item;
 */
@Entity
@Table(name = "ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Schema(description = "Código do item")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ITEM_ID")
    private Long id;

    @Schema(description = "Nome do item")
    @Column(name = "NOME")
    private String name;

    @Schema(description = "Descrição do item")
    @Column(name = "DESCRICAO")
    private String description;

    @Schema(description = "Versão  do item")
    @Column(name = "VERSAO")
    private Long version;

    @Schema(description = "Id do pacote")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PACOTE_ID")
    private Bundle bundle;

    @Schema(description = "Id do status do item")
    @ManyToOne
    @JoinColumn(name = "STATUS_ITEM_ID")
    private StatusItem status;

    @Schema(description = "Id do tipo do item")
    @ManyToOne
    @JoinColumn(name = "TIPO_ITEM_ID")
    private TypeItem type;

    @Schema(description = "Id do produto do item")
    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Product product;

    @Schema(description = "False = versão atual do item, True = versão do item é obsoleta")
    @Column(name = "OBSOLETO")
    private boolean obsolete;
}
