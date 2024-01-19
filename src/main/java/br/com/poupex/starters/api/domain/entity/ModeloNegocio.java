package br.com.poupex.starters.api.domain.entity;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MODELO_NEGOCIO", schema = "API")
public class ModeloNegocio extends AbstractEntity<Long> {

	@Id
	@Column(name = "PK_MDL_NGC")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "COD", nullable = false, length = 50)
	private String codigo;

	@Size(min = 3, max = 100)
	@Column(name = "DSC", nullable = false, length = 100)
	private String descricao;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PESSOA", nullable = false)
	private Pessoa pessoa;

}
