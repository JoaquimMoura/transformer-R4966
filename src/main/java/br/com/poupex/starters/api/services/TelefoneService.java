package br.com.poupex.starters.api.services;

import br.com.poupex.starters.api.commons.support.GenericService;
import br.com.poupex.starters.api.domain.entity.Telefone;
import br.com.poupex.starters.api.repositories.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelefoneService extends GenericService<Telefone, Long> {

    private final TelefoneRepository repository;

    @Override
    public TelefoneRepository getRepository() {
        return repository;
    }
}
