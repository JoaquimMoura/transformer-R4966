package br.com.poupex.starters.api.services;

import br.com.poupex.starters.api.commons.support.GenericService;
import br.com.poupex.starters.api.domain.entity.Pessoa;
import br.com.poupex.starters.api.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService extends GenericService<Pessoa, Long> {

    private final PessoaRepository repository;

    @Override
    public PessoaRepository getRepository() {
        return repository;
    }

    @Override
    public Pessoa preSave(Pessoa pessoa) {
        pessoa.getEmails().forEach(email -> email.setPessoa(pessoa));
        pessoa.getTelefones().forEach(telefone -> telefone.setPessoa(pessoa));
        pessoa.getEndereco().setPessoa(pessoa);
        return pessoa;
    }

    @Transactional
    public void deleteAll(List<Long> ids) {
        ids.forEach(id -> getRepository().deleteById(id));
    }

}
