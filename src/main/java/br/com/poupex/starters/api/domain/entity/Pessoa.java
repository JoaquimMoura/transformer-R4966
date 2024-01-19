package br.com.poupex.starters.api.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.poupex.starters.api.domain.converter.SexoConverter;
import br.com.poupex.starters.api.domain.enumeration.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

	public Pessoa(Long id) {
		this.id = id;
	}

}
