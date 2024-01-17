package br.com.poupex.starters.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "CATEGORIA_MOVIMENTO")
public class Categoria {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

}
