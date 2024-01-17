package br.com.poupex.starters.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.starters.api.commons.support.GenericControllerImpl;
import br.com.poupex.starters.api.controllers.dtos.TelefoneDto;
import br.com.poupex.starters.api.domain.entity.Telefone;
import br.com.poupex.starters.api.services.TelefoneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telefones")
@Tag(name = "TelefoneController")
public class TelefoneController extends GenericControllerImpl<TelefoneDto, Telefone, Long> {

    private final TelefoneService service;

    @Override
    protected TelefoneService getService() {
        return service;
    }
}
