package br.com.poupex.starters.api.controllers;

import br.com.poupex.starters.api.commons.support.GenericControllerImpl;
import br.com.poupex.starters.api.controllers.dtos.EnderecoDto;
import br.com.poupex.starters.api.domain.entity.Endereco;
import br.com.poupex.starters.api.services.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
@Tag(name = "EnderecoController")
public class EnderecoController extends GenericControllerImpl<EnderecoDto, Endereco, Long> {

    private final EnderecoService service;

    @Override
    protected EnderecoService getService() {
        return service;
    }
}
