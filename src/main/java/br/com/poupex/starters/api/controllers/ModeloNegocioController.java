package br.com.poupex.starters.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.poupex.starters.api.commons.api.OpenApiResponsesPadroes;
import br.com.poupex.starters.api.domain.model.input.ModeloNegocioInput;
import br.com.poupex.starters.api.domain.model.output.ModeloNegocioOutput;
import br.com.poupex.starters.api.domain.model.output.ResponseModel;
import br.com.poupex.starters.api.services.ModeloNegocioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "ModeloNegocioController")
@OpenApiResponsesPadroes
@RestController
@RequestMapping(path = "/modelo-negocio", produces = MediaType.APPLICATION_JSON_VALUE)
public class ModeloNegocioController {

	@Autowired
	private final ModeloNegocioService modeloNegocioService;

	@Operation(summary = "Lista todos os Modelos de Negocio com status Ativo. ")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Lista ModeloNegocioOutput ModeloNegocio(status = ATIVO)", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ModeloNegocioOutput.class))) }), })
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ResponseModel> listModelosNegocios() {
		return ResponseEntity.ok(modeloNegocioService.execute());
	}

	@Operation(summary = "Cadastra o Modelos de Negocio. ")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Cadastro realizado", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)),
			@Content(mediaType = "application/json", schema = @Schema(implementation = ModeloNegocioInput.class)) }), })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ResponseModel> create(@RequestBody @Valid final ModeloNegocioInput input) {
		return ResponseEntity.ok(modeloNegocioService.execute(input));
	}

	@Operation(summary = "Exlusão de Modelos de Negocio lógica. ")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Exclusão realizada", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class)), }), })
	@Parameters({ @Parameter(name = "id", description = "Identificador do Modelos de Negocio") })
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
		return ResponseEntity.ok(modeloNegocioService.excluir(id));
	}
}
