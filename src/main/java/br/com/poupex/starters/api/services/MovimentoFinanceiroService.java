package br.com.poupex.starters.api.services;

import org.springframework.stereotype.Service;

import br.com.poupex.starters.api.commons.support.GenericService;
import br.com.poupex.starters.api.domain.entity.MovimentoFinanceiro;
import br.com.poupex.starters.api.repositories.MovimentoFinanceiroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentoFinanceiroService extends GenericService<MovimentoFinanceiro, Long> {

    private final MovimentoFinanceiroRepository repository;

    @Override
    public MovimentoFinanceiroRepository getRepository() {
        return repository;
    }
}
