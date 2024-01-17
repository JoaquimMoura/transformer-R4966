package br.com.poupex.starters.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupex.starters.api.domain.entity.ModeloNegocio;

import java.util.Optional;

public interface ModeloNegocioRepository extends JpaRepository<ModeloNegocio, Long> {

    Optional<ModeloNegocio> findModeloNegocioByCodigoIgnoreCase(String codigo);
    Optional<ModeloNegocio> findModeloNegocioByDescricaoIgnoreCase(String descricao);

}
