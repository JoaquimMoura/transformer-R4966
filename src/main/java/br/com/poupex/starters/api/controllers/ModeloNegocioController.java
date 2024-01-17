package br.com.poupex.starters.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.poupex.starters.api.commons.api.OpenApiPaginacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.starters.api.commons.api.OpenApiResponsesPadroes;
import br.com.poupex.starters.api.domain.model.output.ModeloNegocioOutput;
import br.com.poupex.starters.api.domain.model.output.ResponseModel;
import br.com.poupex.starters.api.services.ObterModeloNegocioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("api/{id}/modelo-negocio")
@RequiredArgsConstructor
@Tag(name = "Modelo Negocio.")
@OpenApiResponsesPadroes
public class ModeloNegocioController {

	private final ObterModeloNegocioService obterModeloNegocioService;


    @Operation(summary = "Lista todos os Modelos de Negocios")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Modelos de Negocios", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
        @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class), array = @ArraySchema(schema = @Schema(implementation = ModeloNegocioOutput.class)))})})
    @OpenApiPaginacao
    @GetMapping
    public ResponseEntity<ResponseModel> read(@Parameter(hidden = true) final Pageable pageable) {
        return ResponseEntity.ok(obterModeloNegocioService.execute(pageable));
    }

	@Operation(summary = "Recuperar o modelo de negocio atraves do ID. ")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Modelo de negócio", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = ModeloNegocioOutput.class)) }), })
	@GetMapping
	public ResponseEntity<ResponseModel> obterModeloNegocio(
			@Parameter(name = "codigo", description = "Codigo do Modelo de Negocio.") @PathVariable("codigo") final String codigo) {
		return ResponseEntity.ok(this.obterModeloNegocioService.getModeloNegocioPorCodigo(codigo));
	}

}
