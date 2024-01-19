package br.com.poupex.starters.api.domain.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ModeloNegocioInput {

	@ApiModelProperty(example = "R4966", value = "Codigo do Modelo de Negocio")
	@NotNull
	private String codigo;

	@ApiModelProperty(example = "Exemplo da Descricao do Modelo de Negocio. ", value = "Descrição do Modelo de Negocio")
	@NotNull
	private String descricao;

}
