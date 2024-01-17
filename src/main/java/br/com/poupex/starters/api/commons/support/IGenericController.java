package br.com.poupex.starters.api.commons.support;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @param <M> DTO (RETORNO DAS OPERAÇÕES DE CONTROLLER)
 * @param <E> ENTITY (ENTIDADE DO BANCO)
 * @param <K> KEY OF ENTITY (ID DA ENTIDADE)
 */
public interface IGenericController<M, E, K> {

    @PostMapping()
    ResponseEntity<M> save(@Validated @RequestBody M input);

    @GetMapping()
    @Parameters({
        @Parameter(name = "page",
            description = "Pagina de resultados que desejar obter."),
        @Parameter(name = "size",
            description = "Número de registros por página."),
        @Parameter(name = "sort",
            description = "Ordenação no seguinte formato: propriedade(asc|desc)." +
                "<br/>Ordenação padrão é ASC." +
                "<br/>Ordenação múltipla é permitida.")})
    ResponseEntity<PageDTO<M>> findAll(
        @PageableDefault(sort = "id") Pageable pageable);

    @GetMapping(value = "/search/find-by-filter")
    @Parameters({
        @Parameter(name = "page",
            description = "Pagina de resultados que desejar obter."),
        @Parameter(name = "size",
            description = "Número de registros por página."),
        @Parameter(name = "sort",
            description = "Ordenação no seguinte formato: propriedade(asc|desc)." +
                "<br/>Ordenação padrão é ASC." +
                "<br/>Ordenação múltipla é permitida.")})
    ResponseEntity<PageDTO<M>> findAllByFilter(M input,
        @PageableDefault(sort = "id") Pageable pageable);

    @GetMapping(value = "{id}")
    ResponseEntity<M> findById(@PathVariable K id);

    @PutMapping(value = "{id}")
    ResponseEntity<M> update(@PathVariable K id, @Validated @RequestBody M input);

    @DeleteMapping(value = "{id}")
    ResponseEntity<?> delete(@PathVariable K id);

}
