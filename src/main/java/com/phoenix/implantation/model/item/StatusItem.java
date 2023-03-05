package com.phoenix.implantation.model.item;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STATUS_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusItem {

    @Schema(description = "Id do status de item")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "STATUS_ITEM_ID")
    private Long id;

    @Schema(description = "nome do status de item")
    @Column(name = "NOME")
    private String name;
}
