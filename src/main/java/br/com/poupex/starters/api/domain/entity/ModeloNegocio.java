package br.com.poupex.starters.api.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "MODELO_NEGOCIO")
public class ModeloNegocio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PK_MDL_NGC")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "COD", nullable = false)
	private String codigo;

	@Size(min = 3, max = 100)
	@Column(name = "DSC", nullable = false)
	private String descricao;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PESSOA", nullable = false)
	private Pessoa pessoa;

}
