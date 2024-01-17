package br.com.poupex.starters.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poupex.starters.api.domain.entity.MovimentoFinanceiro;

public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Long> {

}
