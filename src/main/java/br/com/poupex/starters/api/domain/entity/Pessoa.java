package br.com.poupex.starters.api.domain.entity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.poupex.starters.api.domain.converter.SexoConverter;
import br.com.poupex.starters.api.domain.enumeration.Sexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PESSOA", schema = "API")
public class Pessoa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 200, nullable = false)
    private String nome;

    @Column(name = "CPF", length = 11, nullable = false)
    private String cpf;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "SEXO", nullable = false, length = 1)
    @Convert(converter = SexoConverter.class)
    private Sexo sexo;

    @OneToMany(mappedBy = "pessoa", cascade = { PERSIST, MERGE, REMOVE })
    private Set<Email> emails;

    @OneToMany(mappedBy = "pessoa", cascade = { PERSIST, MERGE, REMOVE }, orphanRemoval = true)
    private Set<Telefone> telefones;

    @OneToOne(mappedBy = "pessoa", cascade = { PERSIST, MERGE, REMOVE })
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoa", cascade = { PERSIST, MERGE, REMOVE })
    private Set<MovimentoFinanceiro> movimentosFinanceiros;
}
