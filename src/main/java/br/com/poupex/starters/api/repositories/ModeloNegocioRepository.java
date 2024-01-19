package br.com.poupex.starters.api.repositories;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.poupex.starters.api.domain.entity.ModeloNegocio;
import br.com.poupex.starters.api.domain.enumeration.StatusEmum;

public interface ModeloNegocioRepository
		extends JpaRepository<ModeloNegocio, Long>, JpaSpecificationExecutor<ModeloNegocio> {

	Optional<ModeloNegocio> findModeloNegocioByCodigoAndDescricaoIgnoreCase(String codigo, String descricao);

	default Specification<ModeloNegocio> id() {
		return (root, query, builder) -> builder.isNotNull(root.get("id"));
	}

	default Specification<ModeloNegocio> status(final StatusEmum status) {
		if (Objects.nonNull(status)) {
			return (root, query, builder) -> builder.equal(root.get("status"), StatusEmum.ATIVO);
		}
		return id();
	}

}
