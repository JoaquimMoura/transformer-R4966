package br.com.poupex.starters.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "EMAIL")
public class Email {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", length = 200, nullable = false)
    private String email;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;
}
