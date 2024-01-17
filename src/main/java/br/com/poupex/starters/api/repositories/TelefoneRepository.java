package br.com.poupex.starters.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupex.starters.api.domain.entity.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
