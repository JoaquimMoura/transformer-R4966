package br.com.poupex.starters.api.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.poupex.starters.api.domain.converter.StatusConverter;
import br.com.poupex.starters.api.domain.enumeration.StatusEmum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractEntity<ID> {

	public abstract void setId(Long id);

	@Enumerated(EnumType.STRING)
	@Column(name = "STA", nullable = false)
    @Convert(converter = StatusConverter.class)
	private StatusEmum status;

	@CreationTimestamp
	@Column(name = "DTH_CRIACAO", nullable = false, updatable = false, columnDefinition = "datetime")
	private LocalDateTime dataCriacao;

	@UpdateTimestamp
	@Column(name = "DTH_ALTERACAO", columnDefinition = "datetime")
	private LocalDateTime dataAlteracao;

	@UpdateTimestamp
	@Column(name = "DTH_INATIVACAO", columnDefinition = "datetime")
	private LocalDateTime dataInativacao;

}
