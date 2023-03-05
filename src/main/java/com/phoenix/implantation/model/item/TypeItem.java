package com.phoenix.implantation.model.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeItem {

    @Schema(description = "Id do tipo de item")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TIPO_ITEM_ID")
    private Long id;
    @Schema(description = "Nome do tipo de item")
    @Column(name = "NOME")
    private String name;
}
