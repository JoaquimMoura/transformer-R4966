package br.com.poupex.starters.api.controllers.dtos;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ModeloNegocio")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ModeloNegocioDto(

		@NotNull String codigo,

		@NotNull String descricao) {
}
