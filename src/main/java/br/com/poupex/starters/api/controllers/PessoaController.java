package br.com.poupex.starters.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.starters.api.commons.support.GenericControllerImpl;
import br.com.poupex.starters.api.controllers.dtos.PessoaDto;
import br.com.poupex.starters.api.domain.entity.Pessoa;
import br.com.poupex.starters.api.services.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
@Tag(name = "PessoaController")
public class PessoaController extends GenericControllerImpl<PessoaDto, Pessoa, Long> {

    private final PessoaService service;

    @Override
    protected PessoaService getService() {
        return service;
    }

    @Override
    public ResponseEntity<PessoaDto> update(@PathVariable Long id, @Validated @RequestBody PessoaDto pessoaDTO) {
        Pessoa pessoa = service.save(dtoToEntity(pessoaDTO));
        return ResponseEntity.ok(entityToDTO(pessoa));
    }
}
