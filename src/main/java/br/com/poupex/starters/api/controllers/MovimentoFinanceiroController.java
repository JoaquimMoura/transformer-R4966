package br.com.poupex.starters.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.starters.api.commons.support.GenericControllerImpl;
import br.com.poupex.starters.api.controllers.dtos.MovimentoFinanceiroDto;
import br.com.poupex.starters.api.domain.entity.MovimentoFinanceiro;
import br.com.poupex.starters.api.services.MovimentoFinanceiroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movimentos-financeiros")
@Tag(name = "MovimentoFinanceiroController")
public class MovimentoFinanceiroController extends GenericControllerImpl<MovimentoFinanceiroDto, MovimentoFinanceiro, Long> {

    private final MovimentoFinanceiroService service;

    @Override
    protected MovimentoFinanceiroService getService() {
        return service;
    }
}
