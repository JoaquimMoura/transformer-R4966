package br.com.poupex.starters.api.services;

import org.springframework.stereotype.Service;

import br.com.poupex.starters.api.commons.support.GenericService;
import br.com.poupex.starters.api.domain.entity.Email;
import br.com.poupex.starters.api.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService extends GenericService<Email, Long> {

    private final EmailRepository repository;

    @Override
    public EmailRepository getRepository() {
        return repository;
    }

}
