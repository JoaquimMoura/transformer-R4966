package br.com.poupex.starters.api.domain.entity;

import br.com.poupex.starters.api.domain.converter.TipoTelefoneConverter;
import br.com.poupex.starters.api.domain.enumeration.TipoTelefone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TELEFONE")
public class Telefone {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO", length = 11, nullable = false)
    private String numero;

    @Column(name = "TIPO_TELEFONE", length = 1, nullable = false)
    @Convert(converter = TipoTelefoneConverter.class)
    private TipoTelefone tipo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;
}
