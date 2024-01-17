package br.com.poupex.starters.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupex.starters.api.domain.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
