package com.phoenix.implantation.model.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PRODUTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Schema(description = "Id do produto")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PRODUTO_ID")
    private Long id;

    @Schema(description = "Nome do produto")
    @Column(name = "NOME")
    private String name;


}
