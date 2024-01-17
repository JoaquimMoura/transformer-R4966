package br.com.poupex.starters.api.services;

import br.com.poupex.starters.api.commons.support.GenericService;
import br.com.poupex.starters.api.domain.entity.Endereco;
import br.com.poupex.starters.api.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService extends GenericService<Endereco, Long> {

    private final EnderecoRepository repository;

    @Override
    public EnderecoRepository getRepository() {
        return repository;
    }
}
