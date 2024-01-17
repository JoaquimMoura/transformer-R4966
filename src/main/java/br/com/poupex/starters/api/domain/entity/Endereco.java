package br.com.poupex.starters.api.domain.entity;

import br.com.poupex.starters.api.domain.enumeration.UF;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ENDERECO", length = 200)
    private String endereco;

    @Column(name = "BAIRRO", length = 100)
    private String bairro;

    @Column(name = "CIDADE", length = 100)
    private String cidade;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "CEP", length = 8)
    private String cep;

    @Column(name = "UF", length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;
}
