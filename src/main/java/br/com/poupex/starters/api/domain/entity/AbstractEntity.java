package br.com.poupex.starters.api.domain.entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import br.com.poupex.starters.api.domain.enumeration.StatusEmum;
import lombok.NoArgsConstructor;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public abstract class AbstractEntity <ID> {

    public abstract void setId(ID id);

	@Enumerated(EnumType.STRING)
	@Column(name = "STA", nullable = false)
	private StatusEmum status;

	@CreationTimestamp
	@Column(name = "DTH_CRIACAO", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCriacao;

	@CreationTimestamp
	@Column(name = "DTH_ALTERACAO", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAlteracao;

	@CreationTimestamp
	@Column(name = "DTH_INATIVACAO", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataInativacao;


}
