package br.com.poupex.starters.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupex.starters.api.domain.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
