package br.com.poupex.starters.api.domain.entity;

import br.com.poupex.starters.api.domain.converter.TipoMovimentoConverter;
import br.com.poupex.starters.api.domain.enumeration.TipoMovimento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "MOVIMENTO_FINANCEIRO")
public class MovimentoFinanceiro {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA", nullable = false)
    private Date data;

    @Column(name = "VALOR", precision = 8, scale = 2, length = 10, nullable = false)
    private BigDecimal valor;

    @Column(name = "TIPO_MOVIMENTO", nullable = false, length = 1)
    @Convert(converter = TipoMovimentoConverter.class)
    private TipoMovimento tipo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CATEGORIA_ID")
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;

}
